package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText emailText;
    Button buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setEnabled(false);
        buttonNext.setBackground(getDrawable(R.color.inactive_blue));
        emailText = findViewById(R.id.emailText);
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkValidation();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    public void checkValidation(){
        emailText = findViewById(R.id.emailText);
        String emText = emailText.getText().toString();
        if (!TextUtils.isEmpty(emText) && Patterns.EMAIL_ADDRESS.matcher(emText).matches()) {
            buttonNext = findViewById(R.id.buttonNext);
            buttonNext.setBackground(getDrawable(R.drawable.rounded_button_active));
            buttonNext.setEnabled(true);
        }
        else {
            buttonNext = findViewById(R.id.buttonNext);
            buttonNext.setBackground(getDrawable(R.drawable.rounded_button_inactive));
            buttonNext.setEnabled(false);
        }
    }
    public void onNextClick(View v){
        startActivity(new Intent(this, EmailVerificationActivity.class));
    }
    public void onYandexClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}