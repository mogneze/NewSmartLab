package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smartlab.activities.CreateChartActivity;
import com.example.smartlab.activities.CreatePasswordActivity;
import com.example.smartlab.activities.LogInActivity;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SmartLab);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        sharedPreferences = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        if(sharedPreferences.contains("email")) startActivity(new Intent(this, CreatePasswordActivity.class));
        else startActivity(new Intent(this, LogInActivity.class));
        finish();
    }
}