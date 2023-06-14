package com.example.smartlab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.items.CategoryItem;
import com.example.smartlab.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Object> listRecyclerItem;
    public CategoryAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView textCategoryTitle;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryTitle = (TextView) itemView.findViewById(R.id.textCategoryTitle);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        return new CategoryAdapter.ItemViewHolder((v));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryAdapter.ItemViewHolder _holder = (CategoryAdapter.ItemViewHolder) holder;
        CategoryItem currentItem = (CategoryItem) listRecyclerItem.get(position);
        _holder.textCategoryTitle.setText(currentItem.getTitle());
        _holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, currentItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
