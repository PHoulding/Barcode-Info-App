package com.example.barcodescannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.barcodescannerapp.ScanCodeActivity.barcode_value;

public class MainActivity extends AppCompatActivity {

    public static TextView resultTextView;
    RelativeLayout relativeLayout;
    Button scan_btn;
    String nameProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView=(TextView) findViewById(R.id.result_text);
        scan_btn= (Button) findViewById(R.id.btn_scan);
        //relativeLayout = findViewById(R.id.r1);

        scan_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScanCodeActivity.class);
                intent.putExtra("result",nameProduct);
                startActivityForResult(intent, 1);
                //startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));

            }
        });

        //resultTextView.setText(nameProduct+"asddsa");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1) {
            if(resultCode==RESULT_OK) {
                //Log.d("asd",data.getStringExtra("result"));
                String result = data.getStringExtra("result");
                resultTextView.setText(result);

                System.out.println("BEFORE "+result);
                if(result!=null) {
                    Intent display_result = new Intent(getApplicationContext(),DisplayResultActivity.class);
                    display_result.putExtra("item",result);
                    startActivity(display_result);
                }
            }
        }
    }
}
