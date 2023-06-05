package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreatePasswordActivity extends AppCompatActivity {

    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
    }

    public void onNumberButtonClick(View v){

    }

    public void onSkipPasswordClick(View v){
        startActivity(new Intent(this, CreateChartActivity.class));
    }
}