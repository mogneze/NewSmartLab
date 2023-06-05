package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateChartActivity extends AppCompatActivity {

    String[] genders = {"М", "Ж"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chart);

        Spinner spinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genders);
        spinner.setAdapter(adapter);
    }
}