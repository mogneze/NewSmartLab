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

public class CartCatalogAdapter extends RecyclerView.Adapter<CartCatalogAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCatalogName, textCatalogPatient, textCatalogPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCatalogName = itemView.findViewById(R.id.textCartTitle);
            textCatalogPatient = itemView.findViewById(R.id.textCartPatient);
            textCatalogPrice = itemView.findViewById(R.id.textCartPrice);
        }
    }
    ArrayList<CatalogItem> list;
    CartCatalogAdapter(ArrayList<CatalogItem> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public CartCatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row, parent, false);
        CartCatalogAdapter.ViewHolder viewHolder = new CartCatalogAdapter.ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatalogItem currentItem = list.get(position);
        TextView textCatalogName = holder.textCatalogName;
        TextView textCatalogPrice = holder.textCatalogPrice;
        TextView textCatalogPatient = holder.textCatalogPatient;

        textCatalogName.setText(currentItem.getName());
        textCatalogPrice.setText(String.valueOf(currentItem.getPrice())+" ₽");

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
