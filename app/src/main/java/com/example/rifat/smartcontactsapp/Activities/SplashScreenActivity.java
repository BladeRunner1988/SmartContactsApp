package com.example.rifat.smartcontactsapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.rifat.smartcontactsapp.Activities.MainActivity;
import com.example.rifat.smartcontactsapp.R;


public class SplashScreenActivity extends Activity {

    boolean splashSeen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 2500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(splashSeen) {
            onBackPressed();
        } else {
            splashSeen = true;
        }
    }
}
