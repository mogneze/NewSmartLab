package com.example.smartlab.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.R;
import com.example.smartlab.fragments.CartFragment;
import com.example.smartlab.items.CatalogItem;

import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{

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
    ArrayList<CatalogItem> list;
    double priceTotal = 0;
    public CatalogAdapter(ArrayList<CatalogItem> list, OnCatalogClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.list = list;
    }
    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_row, parent, false);
        CatalogAdapter.ViewHolder viewHolder = new CatalogAdapter.ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatalogItem currentItem = list.get(position);
        TextView textCatalogName = holder.textCatalogName;
        TextView textCatalogTimeResult = holder.textCatalogTimeResult;
        TextView textCatalogPrice = holder.textCatalogPrice;
        Button btnCatalogAdd = holder.buttonCatalogAdd;

        textCatalogName.setText(currentItem.getName());
        textCatalogTimeResult.setText(currentItem.getTimeResult());
        textCatalogPrice.setText(String.valueOf(currentItem.getPrice())+" ₽");

        holder.buttonCatalogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment cartFragment = new CartFragment();
                if(btnCatalogAdd.getText().toString().equals("Добавить")) {
                    btnCatalogAdd.setText("Удалить");
                    btnCatalogAdd.setTextColor(view.getResources().getColor(R.color.active_blue));
                    btnCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.empty_rounded_button));
                    priceTotal += currentItem.getPrice();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("price", priceTotal);
                    cartFragment.setArguments(bundle);

                    AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCartContainer, cartFragment).commit();
                }
                else{
                    btnCatalogAdd.setText("Добавить");
                    btnCatalogAdd.setTextColor(view.getResources().getColor(R.color.white));
                    btnCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.rounded_button_active));
                    priceTotal -= currentItem.getPrice();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("price", priceTotal);
                    cartFragment.setArguments(bundle);

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
        return list.size();
    }
}
