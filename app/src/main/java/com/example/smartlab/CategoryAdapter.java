package com.example.smartlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    interface OnCategoryClickListener{
        void onCategoryClick(CategoryItem categoryItem, int position);
    }
    private final CategoryAdapter.OnCategoryClickListener onClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCategoryTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryTitle = itemView.findViewById(R.id.textCategoryTitle);
        }
    }
    ArrayList<CategoryItem> list;
    CategoryAdapter(ArrayList<CategoryItem> list, OnCategoryClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.list = list;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryItem currentItem = list.get(position);
        TextView textCategoryTitle = holder.textCategoryTitle;
        textCategoryTitle.setText(currentItem.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickListener.onCategoryClick(currentItem, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
