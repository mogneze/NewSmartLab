package com.example.smartlab.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.R;
import com.example.smartlab.activities.CreateChartActivity;

import java.util.Calendar;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextView birthDate;
    Calendar dateAndTime = Calendar.getInstance();
    EditText editTextAddress, editTextSurname, editTextLastname;
    TextView editTextBirthDate;
    Spinner spinner;
    Button buttonSaveOrCreate;

    String[] genders = {"Мужской", "Женский"};

    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextSurname = view.findViewById(R.id.editTextSurname);
        editTextLastname = view.findViewById(R.id.editTextLastname);
        editTextBirthDate = view.findViewById(R.id.editTextBirthDate);
        spinner = view.findViewById(R.id.spinnerGender);

        sharedPreferences = getContext().getSharedPreferences("CHART", MODE_PRIVATE);
        if(sharedPreferences.contains("surname")) {
            editTextSurname.setText(sharedPreferences.getString("surname", ""));
            buttonSaveOrCreate = view.findViewById(R.id.buttonSaveOrCreate);
            buttonSaveOrCreate.setText("Сохранить");
        }
        if(sharedPreferences.contains("lastname")) editTextLastname.setText(sharedPreferences.getString("lastname", ""));
        if(sharedPreferences.contains("birthdate")) editTextBirthDate.setText(sharedPreferences.getString("birthdate", ""));
        if(sharedPreferences.contains("address")) editTextAddress.setText(sharedPreferences.getString("address", ""));
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genders);
        spinner.setAdapter(adapter);
        if(sharedPreferences.contains("gender")){
            if(sharedPreferences.getString("gender", "").equals("Женский")) spinner.setSelection(1);
            else spinner.setSelection(0);
        }

            TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String addressText = editTextAddress.getText().toString();
                String surnameText = editTextSurname.getText().toString();
                String lastnameText = editTextLastname.getText().toString();
                String birthdateText = editTextBirthDate.getText().toString();
                buttonSaveOrCreate = view.findViewById(R.id.buttonSaveOrCreate);
                if (!TextUtils.isEmpty(addressText) && !TextUtils.isEmpty(surnameText) && !TextUtils.isEmpty(lastnameText) && !TextUtils.isEmpty(birthdateText)) {
                    buttonSaveOrCreate.setBackground(getContext().getDrawable(R.drawable.rounded_button_active));
                    buttonSaveOrCreate.setEnabled(true);
                }
                else {
                    buttonSaveOrCreate.setBackground(getContext().getDrawable(R.drawable.rounded_button_inactive));
                    buttonSaveOrCreate.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        editTextAddress.addTextChangedListener(textWatcher);
        editTextSurname.addTextChangedListener(textWatcher);
        editTextLastname.addTextChangedListener(textWatcher);
        editTextBirthDate.addTextChangedListener(textWatcher);

        buttonSaveOrCreate = view.findViewById(R.id.buttonSaveOrCreate);
        buttonSaveOrCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getContext().getSharedPreferences("CHART", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("address", editTextAddress.getText().toString());
                editor.putString("surname", editTextSurname.getText().toString());
                editor.putString("lastname", editTextLastname.getText().toString());
                editor.putString("birthdate", editTextBirthDate.getText().toString());
                editor.putString("gender", spinner.getSelectedItem().toString());
                editor.commit();

                Toast.makeText(getContext(), "Изменения сохранены", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView profilePic = view.findViewById(R.id.imgProfilePicture);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ну картинка типо", Toast.LENGTH_SHORT).show();
            }
        });

        birthDate = view.findViewById(R.id.editTextBirthDate);
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                birthDate = view.findViewById(R.id.editTextBirthDate);
                birthDate.setText(String.valueOf(DateUtils.formatDateTime(getContext(), dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE)));
            }
        };
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), listener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }
}