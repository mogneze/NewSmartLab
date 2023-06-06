package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CatalogAdapter extends BaseAdapter {
    private ArrayList<CatalogItem> data;
    private LayoutInflater inflater;
    public CatalogAdapter(Context context, ArrayList<CatalogItem> data){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) view = inflater.inflate(R.layout.catalog_row, null);
        TextView name = view.findViewById(R.id.textCatalogName);
        TextView time = view.findViewById(R.id.textCatalogTimeResult);
        TextView price = view.findViewById(R.id.textCatalogPrice);
        CatalogItem item = data.get(position);
        name.setText(item.getName());
        time.setText(item.getTimeResult());
        price.setText(String.valueOf(item.getPrice())+" ₽");
        return view;
    }
}
