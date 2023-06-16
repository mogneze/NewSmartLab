package com.example.smartlab.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.R;
import com.example.smartlab.fragments.CartFragment;
import com.example.smartlab.items.CatalogItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Object> listRecyclerItem;
    double priceTotal = 0;

    ArrayList<String> arrPackage;
    SharedPreferences cartItems;
    public CatalogAdapter(Context context, List<Object> listRecyclerItem, OnCatalogClickListener onClickListener) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.onClickListener = onClickListener;
    }
    public interface OnCatalogClickListener{
        void onCatalogClick(CatalogItem catalogItem, int position);
    }
    private final OnCatalogClickListener onClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCatalogName, textCatalogTimeResult, textCatalogPrice;
        public Button buttonCatalogAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCatalogName = itemView.findViewById(R.id.textCatalogName);
            textCatalogTimeResult = itemView.findViewById(R.id.textCatalogTimeResult);
            textCatalogPrice = itemView.findViewById(R.id.textCatalogPrice);
            buttonCatalogAdd = itemView.findViewById(R.id.btnCatalogAdd);
        }
    }
    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_row, parent, false);
        return new CatalogAdapter.ViewHolder((v));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder _holder = (ViewHolder) holder;
        CatalogItem currentItem = (CatalogItem) listRecyclerItem.get(position);

        _holder.textCatalogName.setText(currentItem.getName());
        _holder.textCatalogTimeResult.setText(currentItem.getTimeResult());
        _holder.textCatalogPrice.setText(String.valueOf(currentItem.getPrice())+" ₽");

        arrPackage = new ArrayList<>();
        cartItems = context.getSharedPreferences("ITEMS", MODE_PRIVATE);
        _holder.buttonCatalogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment cartFragment = new CartFragment();
                if(_holder.buttonCatalogAdd.getText().toString().equals("Добавить")) {
                    _holder.buttonCatalogAdd.setText("Удалить");
                    _holder.buttonCatalogAdd.setTextColor(view.getResources().getColor(R.color.active_blue));
                    _holder.buttonCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.empty_rounded_button));
                    priceTotal += Double.parseDouble(currentItem.getPrice());
                    Bundle bundle = new Bundle();
                    bundle.putDouble("price", priceTotal);
                    cartFragment.setArguments(bundle);

                    arrPackage.add(String.valueOf(currentItem.getId()));
                    arrPackage.add(String.valueOf(currentItem.getCategory()));
                    arrPackage.add(String.valueOf(currentItem.getName()));
                    arrPackage.add(String.valueOf(currentItem.getDescription()));
                    arrPackage.add(String.valueOf(currentItem.getPrice()));
                    arrPackage.add(String.valueOf(currentItem.getTimeResult()));
                    arrPackage.add(String.valueOf(currentItem.getPreparation()));
                    arrPackage.add(String.valueOf(currentItem.getBio()));
                    Gson gson = new Gson();
                    String json = gson.toJson(arrPackage);
                    SharedPreferences.Editor editor = cartItems.edit();
                    editor.putString("item " + ((String.valueOf(currentItem.getId()))), json);
                    editor.commit();

                    AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCartContainer, cartFragment).commit();
                }
                else{
                    _holder.buttonCatalogAdd.setText("Добавить");
                    _holder.buttonCatalogAdd.setTextColor(view.getResources().getColor(R.color.white));
                    _holder.buttonCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.rounded_button_active));
                    priceTotal -= Double.parseDouble(currentItem.getPrice());
                    Bundle bundle = new Bundle();
                    bundle.putDouble("price", priceTotal);
                    cartFragment.setArguments(bundle);

                    Gson gson = new Gson();
                    String json = gson.toJson(arrPackage);
                    SharedPreferences.Editor editor = cartItems.edit();
                    editor.clear();
                    editor.commit();

                    AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCartContainer, cartFragment).commit();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickListener.onCatalogClick(currentItem, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
