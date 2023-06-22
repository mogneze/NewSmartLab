package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OrderingActivity extends AppCompatActivity {

    String[] patients = {"Тицкий Эдуард", "Петров Пётр"};
    TextView addressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        Spinner spinner = findViewById(R.id.spinnerPatients);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, patients);
        spinner.setAdapter(adapter);

        addressText = findViewById(R.id.editTextAddress);
        addressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(R.layout.dialog_address);
                bottomSheetDialog.show();
            }
        });
    }
}