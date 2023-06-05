package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmailVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
    }

    public void onBackClick(View v){
        startActivity(new Intent(this, LogInActivity.class));
    }
    public void onTempClick(View v){
        startActivity(new Intent(this, CreatePasswordActivity.class));
    }
}