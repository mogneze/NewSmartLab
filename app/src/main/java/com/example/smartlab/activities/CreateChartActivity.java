package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartlab.MainActivity;
import com.example.smartlab.R;

import java.util.Calendar;

public class CreateChartActivity extends AppCompatActivity {

    String[] genders = {"М", "Ж"};
    TextView birthDate;
    Calendar dateAndTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chart);

        Spinner spinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genders);
        spinner.setAdapter(adapter);
    }
    public void setDate(View v) {
        new DatePickerDialog(CreateChartActivity.this, listener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
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
        birthDate.setText(String.valueOf(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE)));
    }
    public void onSkipChartClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void onCreateChartClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}