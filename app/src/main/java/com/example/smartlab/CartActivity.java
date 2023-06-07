package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<CatalogItem> catalogItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initData();

        RecyclerView catalogRecyclerView = findViewById(R.id.recyclerViewCartItems);
        CartCatalogAdapter catalogAdapter = new CartCatalogAdapter(catalogItemList);
        catalogRecyclerView.setAdapter(catalogAdapter);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
    }


    public void initData(){
        catalogItemList = new ArrayList<>();
        catalogItemList.add(new CatalogItem(1, "ПЦР-тест на определение РНК коронавируса стандартный", "des", 1800, "2 дня", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "Клинический анализ крови с лейкоцитарной формулировкой", "des", 2000, "1 день", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "Биохимический анализ крови, базовый", "des", 2440, "1 день", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "СОЭ (венозная кровь)", "des", 1800, "1 день", "prep", "bio"));
    }

    public void onCartBackClick(View v){
        finish();
    }
}