package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCatalogName;
        public TextView textCatalogTimeResult;
        public TextView textCatalogPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCatalogName = itemView.findViewById(R.id.textCatalogName);
            textCatalogTimeResult = itemView.findViewById(R.id.textCatalogTimeResult);
            textCatalogPrice = itemView.findViewById(R.id.textCatalogPrice);
        }
    }
    ArrayList<CatalogItem> list;
    public CatalogAdapter(ArrayList<CatalogItem> list) {
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
        textCatalogName.setText(currentItem.getName());
        textCatalogTimeResult.setText(currentItem.getTimeResult());
        textCatalogPrice.setText(String.valueOf(currentItem.getPrice())+" ₽");
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
