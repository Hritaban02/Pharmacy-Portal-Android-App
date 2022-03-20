package com.example.pharmacyportal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent loginIntent;
                if (DBHelper.userId <= 0) {
                    loginIntent = new Intent(getApplicationContext(), Login.class);
                } else {
                    loginIntent = new Intent(getApplicationContext(), Home.class);
                }
                startActivity(loginIntent);
                finish();

            }
        }, 1000);

    }
}