package com.example.smartlab.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.R;
import com.example.smartlab.adapters.CartCatalogAdapter;
import com.example.smartlab.items.CatalogItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ArrayList<CatalogItem> catalogItemList;
    TextView textTotal;
    Button btnGoToOrder;
    CartCatalogAdapter catalogAdapter;
    SharedPreferences items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        textTotal = findViewById(R.id.textCartTotal);
        initData();
        RecyclerView catalogRecyclerView = findViewById(R.id.recyclerViewCartItems);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        catalogAdapter = new CartCatalogAdapter(getApplicationContext(), textTotal, catalogItemList);
        catalogRecyclerView.setAdapter(catalogAdapter);

        textTotal.setText(Calculate()+" ₽");
        btnGoToOrder = findViewById(R.id.btnGoToOrder);
        btnGoToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrderingActivity.class));
            }
        });
    }
    public double Calculate(){
        double sum = 0;
        for(int i = 0; i < catalogItemList.size(); i++){
            sum += Double.parseDouble(String.valueOf(catalogItemList.get(i).getPrice()));
        }
        return sum;
    }
    public void initData(){
        items = getSharedPreferences("ITEMS", MODE_PRIVATE);
        catalogItemList = new ArrayList<>();
        Gson gson = new Gson();

        for (int i=1; i<20; i++) {
            String json;
            if(items.contains("item "+i)){
                json = items.getString("item " +i, "default");
                Type type = new TypeToken<List<String>>(){}.getType();
                List<String> arrPackageData = gson.fromJson(json, type);
                ArrayList<String> newItem = new ArrayList<>(arrPackageData);
                CatalogItem catalogItem = new CatalogItem(Integer.parseInt(newItem.get(0)), Integer.parseInt(newItem.get(1)), newItem.get(2), newItem.get(3), newItem.get(4), newItem.get(5), newItem.get(6), newItem.get(7), Integer.parseInt(newItem.get(8)));
                catalogItemList.add(catalogItem);
            }
        }
    }
    public void onCartBackClick(View v){
        finish();
    }
    public void onClearClick(View v){
        SharedPreferences cartItems;
        cartItems = getSharedPreferences("ITEMS", MODE_PRIVATE);
        SharedPreferences.Editor editor = cartItems.edit();
        editor.clear();
        editor.commit();
        catalogItemList.clear();
        catalogAdapter.notifyDataSetChanged();

        textTotal = findViewById(R.id.textCartTotal);
        textTotal.setText(Calculate()+" ₽");
    }
}