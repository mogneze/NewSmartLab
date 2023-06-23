package com.example.smartlab.activities;

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

import com.example.smartlab.R;

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
                editN2.requestFocus();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        editN1.addTextChangedListener(textWatcher);
        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editN3.requestFocus();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        editN2.addTextChangedListener(textWatcher2);
        TextWatcher textWatcher3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editN4.requestFocus();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        editN3.addTextChangedListener(textWatcher3);
        TextWatcher textWatcher4 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                startActivity(new Intent(getApplicationContext(), CreatePasswordActivity.class));
                finish();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        editN4.addTextChangedListener(textWatcher4);
    }
    public void createTimer(){
        textSendCode = findViewById(R.id.textSendCode);
        new CountDownTimer(60000, 1000) {
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
}