package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartlab.MainActivity;
import com.example.smartlab.MainPageFragment;
import com.example.smartlab.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LogInActivity extends AppCompatActivity {
    EditText emailText;
    Button buttonNext;

    public void onNextClick(View v) {
        startActivity(new Intent(this, EmailVerificationActivity.class));
    }
    public void onYandexClick(View v){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        buttonNext = findViewById(R.id.btnGoToVerification);
        buttonNext.setEnabled(false);
        buttonNext.setBackground(getDrawable(R.drawable.rounded_button_inactive));
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
            buttonNext = findViewById(R.id.btnGoToVerification);
            buttonNext.setBackground(getDrawable(R.drawable.rounded_button_active));
            buttonNext.setEnabled(true);
        }
        else {
            buttonNext = findViewById(R.id.btnGoToVerification);
            buttonNext.setBackground(getDrawable(R.drawable.rounded_button_inactive));
            buttonNext.setEnabled(false);
        }
    }
}