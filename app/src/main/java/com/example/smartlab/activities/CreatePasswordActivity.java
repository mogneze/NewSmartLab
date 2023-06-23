package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.R;
import com.example.smartlab.activities.CreateChartActivity;

public class CreatePasswordActivity extends AppCompatActivity {
    String password = "";
    ImageView dot1, dot2, dot3, dot4;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        sharedPreferences = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        if(sharedPreferences.contains("password")){
            TextView tx = findViewById(R.id.textCreatePassword);
            tx.setText("Введите пароль");
        }
    }

    public void onNumberButtonClick(View v){
        Button button = (Button) v;
        if(password.length() < 4) password += button.getText();
        checkPasswordLength();
    }

    public void onDeleteButtonClick(View v){
        if(password.length() == 1)password = "";
        if(!password.equals("")) password = password.substring(0, password.length()-1);
        checkPasswordLength();
    }

    public void checkPasswordLength(){
        dot1 = findViewById(R.id.passwordDot1);
        dot2 = findViewById(R.id.passwordDot2);
        dot3 = findViewById(R.id.passwordDot3);
        dot4 = findViewById(R.id.passwordDot4);
        switch (password.length()){
            case 1:
                dot1.setImageResource(R.drawable.circle_filled);
                dot2.setImageResource(R.drawable.circle);
                dot3.setImageResource(R.drawable.circle);
                dot4.setImageResource(R.drawable.circle);
                break;
            case 2:
                dot1.setImageResource(R.drawable.circle_filled);
                dot2.setImageResource(R.drawable.circle_filled);
                dot3.setImageResource(R.drawable.circle);
                dot4.setImageResource(R.drawable.circle);
                break;
            case 3:
                dot1.setImageResource(R.drawable.circle_filled);
                dot2.setImageResource(R.drawable.circle_filled);
                dot3.setImageResource(R.drawable.circle_filled);
                dot4.setImageResource(R.drawable.circle);
                break;
            case 4:
                dot1.setImageResource(R.drawable.circle_filled);
                dot2.setImageResource(R.drawable.circle_filled);
                dot3.setImageResource(R.drawable.circle_filled);
                dot4.setImageResource(R.drawable.circle_filled);

                sharedPreferences = getSharedPreferences("SETTINGS", MODE_PRIVATE);
                if(sharedPreferences.contains("password")){
                    if(sharedPreferences.getString("password", "0000").equals(password)){
                        startActivity(new Intent(getApplicationContext(), CreateChartActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                        password = "";
                        checkPasswordLength();
                    }
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("password", password);
                    editor.commit();
                }

                break;
            default:
                dot1.setImageResource(R.drawable.circle);
                dot2.setImageResource(R.drawable.circle);
                dot3.setImageResource(R.drawable.circle);
                dot4.setImageResource(R.drawable.circle);
                break;
        }
    }
    public void onSkipPasswordClick(View v){
        startActivity(new Intent(this, CreateChartActivity.class));
        finish();
    }

}