package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void onNextClick(View v){
        startActivity(new Intent(this, EmailVerificationActivity.class));
    }
    public void onYandexClick(View v){
        Toast.makeText(this, "не нажимать", Toast.LENGTH_SHORT).show();
    }
}