package com.belajar.assignmentdatabinding.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.belajar.assignmentdatabinding.R;

public class SplashScreen extends AppCompatActivity {
    private int time_load=3000;
    public static Boolean syarat;
    SharedPreferences sharedPreferences;
    public static final String SESS_SYARAT = "syarat";
    public static final String my_shared = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(my_shared, Context.MODE_PRIVATE);
        syarat = sharedPreferences.getBoolean(SESS_SYARAT, false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },time_load);
    }
}