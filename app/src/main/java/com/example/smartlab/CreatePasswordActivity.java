package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CreatePasswordActivity extends AppCompatActivity {

    String password = "";
    ImageView dot1, dot2, dot3, dot4;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
    }

    public void onNumberButtonClick(View v){
        password += "1";
        checkPasswordLength();
        textView = findViewById(R.id.textCreatePassword);
        textView.setText(password);
    }

    public void onDeleteButtonClick(View v){

    }

    public void checkPasswordLength(){
        switch (password.length()){
            case 1:
                dot1 = findViewById(R.id.passwordDot1);
                dot1.setImageResource(R.drawable.circle_filled);
                break;
            case 2:
                dot2 = findViewById(R.id.passwordDot2);
                dot2.setImageResource(R.drawable.circle_filled);
                break;
            case 3:
                dot3 = findViewById(R.id.passwordDot3);
                dot3.setImageResource(R.drawable.circle_filled);
                break;
            case 4:
                dot4 = findViewById(R.id.passwordDot4);
                dot4.setImageResource(R.drawable.circle_filled);
                break;
        }
    }
    public void onSkipPasswordClick(View v){
        startActivity(new Intent(this, CreateChartActivity.class));
    }

}