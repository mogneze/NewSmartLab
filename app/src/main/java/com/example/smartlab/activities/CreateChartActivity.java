package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.MainActivity;
import com.example.smartlab.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateChartActivity extends AppCompatActivity {

    String[] genders = {"Мужской", "Женский"};
    TextView birthDate;
    Calendar dateAndTime = Calendar.getInstance();
    EditText editTextAddress, editTextSurname, editTextLastname;
    TextView editTextBirthDate;
    Spinner spinner;
    Button buttonCreate;

    SharedPreferences sharedPreferences;

    ArrayList<String> arrPackage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chart);

        spinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genders);
        spinner.setAdapter(adapter);

        editTextAddress = findViewById(R.id.editTextAddress);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextLastname = findViewById(R.id.editTextLastname);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String addressText = editTextAddress.getText().toString();
                String surnameText = editTextSurname.getText().toString();
                String lastnameText = editTextLastname.getText().toString();
                String birthdateText = editTextBirthDate.getText().toString();
                buttonCreate = findViewById(R.id.buttonCreate);
                if (!TextUtils.isEmpty(addressText) && !TextUtils.isEmpty(surnameText) && !TextUtils.isEmpty(lastnameText) && !TextUtils.isEmpty(birthdateText)) {
                    buttonCreate.setBackground(getDrawable(R.drawable.rounded_button_active));
                    buttonCreate.setEnabled(true);
                }
                else {
                    buttonCreate.setBackground(getDrawable(R.drawable.rounded_button_inactive));
                    buttonCreate.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        editTextAddress.addTextChangedListener(textWatcher);
        editTextSurname.addTextChangedListener(textWatcher);
        editTextLastname.addTextChangedListener(textWatcher);
        editTextBirthDate.addTextChangedListener(textWatcher);
    }

    public void setDate(View v) {
        new DatePickerDialog(CreateChartActivity.this, listener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
    private void setInitialDateTime() {
        birthDate = findViewById(R.id.editTextBirthDate);
        birthDate.setText(String.valueOf(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE)));
    }
    public void onSkipChartClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void onCreateChartClick(View v){
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextLastname = findViewById(R.id.editTextLastname);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        spinner = findViewById(R.id.spinnerGender);

        sharedPreferences = getSharedPreferences("CHART", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address", editTextAddress.getText().toString());
        editor.putString("surname", editTextSurname.getText().toString());
        editor.putString("lastname", editTextLastname.getText().toString());
        editor.putString("birthdate", editTextBirthDate.getText().toString());
        editor.putString("gender", spinner.getSelectedItem().toString());
        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}