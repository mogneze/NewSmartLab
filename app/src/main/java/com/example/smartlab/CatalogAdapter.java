package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{

    interface OnCatalogClickListener{
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
    CatalogAdapter(ArrayList<CatalogItem> list, OnCatalogClickListener onClickListener) {
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
                if(btnCatalogAdd.getText().toString().equals("Добавить")) {
                    btnCatalogAdd.setText("Удалить");
                    btnCatalogAdd.setTextColor(view.getResources().getColor(R.color.active_blue));
                    btnCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.empty_rounded_button));
                }
                else{
                    btnCatalogAdd.setText("Добавить");
                    btnCatalogAdd.setTextColor(view.getResources().getColor(R.color.white));
                    btnCatalogAdd.setBackground(view.getResources().getDrawable(R.drawable.rounded_button_active));
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
