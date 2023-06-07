package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class EmailVerificationActivity extends AppCompatActivity {

    TextView textSendCode;
    EditText editN1, editN2, editN3, editN4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        createTimer();
        editN1 = findViewById(R.id.editTextN1);
        editN2 = findViewById(R.id.editTextN2);
        editN3 = findViewById(R.id.editTextN3);
        editN4 = findViewById(R.id.editTextN4);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        editN1.addTextChangedListener(textWatcher);
    }
    public void createTimer(){
        textSendCode = findViewById(R.id.textSendCode);
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                textSendCode.setText("Отправить код повторно можно будет через " + millisUntilFinished / 1000 + " секунд");
            }
            public void onFinish() {
                textSendCode.setText("Отправить код повторно");
                textSendCode.setOnClickListener(onClickListener);
            }
        }.start();
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            textSendCode = findViewById(R.id.textSendCode);
            Toast.makeText(EmailVerificationActivity.this, "test", Toast.LENGTH_SHORT).show();
            textSendCode.setOnClickListener(null);
            createTimer();
        }
    };

    public void onBackClick(View v){
        startActivity(new Intent(this, LogInActivity.class));
    }
    public void onTempClick(View v){
        startActivity(new Intent(this, CreatePasswordActivity.class));
        finish();
    }
}