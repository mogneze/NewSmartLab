package com.example.smartlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smartlab.MainPageFragment;
import com.example.smartlab.activities.CartActivity;
import com.example.smartlab.fragments.ProfileFragment;
import com.example.smartlab.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FragmentContainerView fragmentContainerView;

    MainPageFragment mainPageFragment = new MainPageFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.analysesMenuItem);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
        switch (item.getItemId()){
            case R.id.analysesMenuItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, mainPageFragment).commit();
                return true;
            case R.id.resultsMenuItem:
                startActivity(new Intent(this, CartActivity.class));
                return true;
            case R.id.supportMenuItem:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profileMenuItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, profileFragment).commit();
                return true;
        }
        return false;
    }
}