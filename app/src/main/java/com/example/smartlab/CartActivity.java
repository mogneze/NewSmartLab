package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ArrayList<CatalogItem> catalogItemList;
    TextView textTotal;
    CartCatalogAdapter catalogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initData();
        RecyclerView catalogRecyclerView = findViewById(R.id.recyclerViewCartItems);
        catalogAdapter = new CartCatalogAdapter(catalogItemList);
        catalogRecyclerView.setAdapter(catalogAdapter);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        textTotal = findViewById(R.id.textCartTotal);
        textTotal.setText(String.valueOf(Calculate())+" ₽");
    }
    public double Calculate(){
        double sum = 0;
        for(int i = 0; i < catalogItemList.size(); i++){
            sum += Double.parseDouble(String.valueOf(catalogItemList.get(i).getPrice()));
        }
        return sum;
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
    public void onClearClick(View v){
        catalogItemList.clear();
        catalogAdapter.notifyDataSetChanged();
        textTotal = findViewById(R.id.textCartTotal);
        textTotal.setText(String.valueOf(Calculate())+ " ₽");
    }
}