package com.example.smartlab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.R;
import com.example.smartlab.items.CategoryItem;
import com.example.smartlab.items.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Object> listRecyclerItem;
    public NewsAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView textTitle, textDescription, textPrice;
        public ImageView image;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textNewsTitle);
            textDescription = itemView.findViewById(R.id.textNewsComment);
            textPrice = itemView.findViewById(R.id.textNewsPrice);
            image = itemView.findViewById(R.id.imageNews);
        }
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false);
        return new NewsAdapter.ItemViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder _holder = (ItemViewHolder) holder;
        NewsItem currentItem = (NewsItem) listRecyclerItem.get(position);
        _holder.textTitle.setText(currentItem.getTitle());
        _holder.textDescription.setText(currentItem.getDescription());
        _holder.textPrice.setText(String.valueOf(currentItem.getPrice()));
        Picasso.get()
                .load(currentItem.getImage())
                .placeholder(R.drawable.men)
                .error(R.drawable.analyses_icon)
                .into(_holder.image);
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
