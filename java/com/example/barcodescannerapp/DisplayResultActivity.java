package com.example.barcodescannerapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

//package com.javapapers.java.nio;


public class DisplayResultActivity extends AppCompatActivity {

        RelativeLayout relativeLayout;
        TextView resultTextView;
        String nameProduct;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            relativeLayout = findViewById(R.id.rl);

            Intent intent = getIntent();
            nameProduct = intent.getStringExtra("item");
            System.out.println("DASNFKL0ASNFDKJN "+nameProduct.split(" ")[0]+" lansdlknasdnaskjd");
            if (nameProduct.split(" ")[0].equalsIgnoreCase("Kaltgepresstes")) {
                relativeLayout.setBackgroundResource(R.drawable.canolaoil);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("Balisto")) {
                relativeLayout.setBackgroundResource(R.drawable.bars);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("Apfelsaft")) {
                relativeLayout.setBackgroundResource(R.drawable.applejuice);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("Keimster")) {
                relativeLayout.setBackgroundResource(R.drawable.cerealflake);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("BackFamily")) {
                relativeLayout.setBackgroundResource(R.drawable.hazelnutbrittle);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("Herrencreme")) {
                relativeLayout.setBackgroundResource(R.drawable.showergel);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("Ekologisk")) {
                relativeLayout.setBackgroundResource(R.drawable.strawberryjame);
            }
            else if (nameProduct.split(" ")[0].equalsIgnoreCase("ja!")) {
                relativeLayout.setBackgroundResource(R.drawable.toiletpaper);
            }
            }
        }
