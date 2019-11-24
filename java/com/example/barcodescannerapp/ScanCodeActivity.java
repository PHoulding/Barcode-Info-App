package com.example.barcodescannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Cache;
import com.android.volley.Network;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.barcodescannerapp.MainActivity.resultTextView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;
    public static String barcode_value;
    public static String response_val;
    public static String detail_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result){
        RequestQueue queue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache,network);
        queue.start();

        String userId="764880122381645668007"; //At some point, dont store this as plain text
        String url = "http://opengtindb.org/?ean="+result.getText()+"&cmd=query&queryid="+userId;
        StringRequest request = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response_val=response;
                String lines[] = response_val.split("\\r?\\n");
                detail_name=lines[5].split("=")[1];

                //resultTextView.setText(detail_name);
                //barcode_value = resultTextView.getText().toString();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError err) {
                if(err instanceof TimeoutError || err instanceof NoConnectionError) {
                    response_val = "timeout/no connection";
                    resultTextView.setText(response_val);
                    barcode_value = resultTextView.getText().toString();
                    Log.d("Result", barcode_value);
                } else if(err instanceof AuthFailureError) {
                    response_val = "authentication failure";
                    resultTextView.setText(response_val);
                    barcode_value = resultTextView.getText().toString();
                    Log.d("Result", barcode_value);
                } else if(err instanceof ServerError) {
                    response_val = "server error";
                    resultTextView.setText(response_val);
                    barcode_value = resultTextView.getText().toString();
                    Log.d("Result", barcode_value);
                } else if(err instanceof NetworkError) {
                    response_val = "network error";
                    resultTextView.setText(response_val);
                    barcode_value = resultTextView.getText().toString();
                    Log.d("Result", barcode_value);
                } else if(err instanceof ParseError) {
                    response_val = "Parse error";
                    resultTextView.setText(response_val);
                    barcode_value = resultTextView.getText().toString();
                    Log.d("Result", barcode_value);
                }
            }
        });
        queue.add(request);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result",detail_name);
        //Log.d("before pass",detail_name);
        setResult(RESULT_OK,intent);

        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }

}
