package com.example.smartlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    interface OnNewsClickListener{
        void onNewsClick(NewsItem newsItem, int position);
    }
    private final OnNewsClickListener onClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textTitle;
        public TextView textComment;
        public TextView textPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textNewsTitle);
            textComment = itemView.findViewById(R.id.textNewsComment);
            textPrice = itemView.findViewById(R.id.textNewsPrice);
        }
    }
    ArrayList<NewsItem> list;
    NewsAdapter(ArrayList<NewsItem> list, OnNewsClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem currentItem = list.get(position);
        TextView textTitle = holder.textTitle;
        TextView textComment = holder.textComment;
        TextView textPrice = holder.textPrice;
        textTitle.setText(currentItem.getTitle());
        textComment.setText(currentItem.getComment());
        textPrice.setText(String.valueOf(currentItem.getPrice())+" ₽");
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickListener.onNewsClick(currentItem, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
