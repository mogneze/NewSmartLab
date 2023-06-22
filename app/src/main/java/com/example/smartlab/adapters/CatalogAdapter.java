package com.example.smartlab.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.R;
import com.example.smartlab.items.CatalogItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Object> listRecyclerItem;
    private boolean[] addedItems;

    ArrayList<String> arrPackage;
    SharedPreferences cartItems;
    ConstraintLayout cartWidget;
    TextView txPrice;
    public CatalogAdapter(Context context, List<Object> listRecyclerItem,ConstraintLayout cartWidget, TextView txPrice, boolean[] addedItems, OnCatalogClickListener onClickListener) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.onClickListener = onClickListener;
        this.addedItems = new boolean[16];
        this.cartWidget = cartWidget;
        this.txPrice = txPrice;
    }
    public interface OnCatalogClickListener{
        void onCatalogClick(CatalogItem catalogItem, int position);
    }
    private final OnCatalogClickListener onClickListener;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCatalogName, textCatalogTimeResult, textCatalogPrice;
        public Button buttonCatalogAdd;
        public ConstraintLayout cartWidget;
        public TextView textTotalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCatalogName = itemView.findViewById(R.id.textCatalogName);
            textCatalogTimeResult = itemView.findViewById(R.id.textCatalogTimeResult);
            textCatalogPrice = itemView.findViewById(R.id.textCatalogPrice);
            buttonCatalogAdd = itemView.findViewById(R.id.btnCatalogAdd);

            cartWidget = itemView.findViewById(R.id.cartWidget);
            textTotalPrice = itemView.findViewById(R.id.textPriceToCart);
        }
    }
    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_row, parent, false);

        return new CatalogAdapter.ViewHolder((v));
    }
    private void pressButton(int position, ViewHolder _holder, CatalogItem currentItem){
        arrPackage = new ArrayList<>();
        if(addedItems != null && addedItems[position]){
            _holder.buttonCatalogAdd.setText("Удалить");
            _holder.buttonCatalogAdd.setTextColor(context.getResources().getColor(R.color.active_blue));
            _holder.buttonCatalogAdd.setBackground(context.getResources().getDrawable(R.drawable.empty_rounded_button));

            arrPackage.add(String.valueOf(currentItem.getId()));
            arrPackage.add(String.valueOf(currentItem.getCategory()));
            arrPackage.add(String.valueOf(currentItem.getName()));
            arrPackage.add(String.valueOf(currentItem.getDescription()));
            arrPackage.add(String.valueOf(currentItem.getPrice()));
            arrPackage.add(String.valueOf(currentItem.getTimeResult()));
            arrPackage.add(String.valueOf(currentItem.getPreparation()));
            arrPackage.add(String.valueOf(currentItem.getBio()));
            arrPackage.add(String.valueOf(currentItem.getPatients()));

            Gson gson = new Gson();
            String json = gson.toJson(arrPackage);
            SharedPreferences.Editor editor = cartItems.edit();
            editor.putString("item " + currentItem.getId(), json);
            editor.commit();

            addedItems[position] = false;
        }
        else{
            _holder.buttonCatalogAdd.setText("Добавить");
            _holder.buttonCatalogAdd.setTextColor(context.getResources().getColor(R.color.white));
            _holder.buttonCatalogAdd.setBackground(context.getResources().getDrawable(R.drawable.rounded_button_active));

            SharedPreferences.Editor editor = cartItems.edit();
            editor.remove("item " + currentItem.getId());
            editor.commit();

            addedItems[position] = true;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ViewHolder _holder = (ViewHolder) holder;
        CatalogItem currentItem = (CatalogItem) listRecyclerItem.get(position);

        _holder.textCatalogName.setText(currentItem.getName());
        _holder.textCatalogTimeResult.setText(currentItem.getTimeResult());
        _holder.textCatalogPrice.setText(currentItem.getPrice() +" ₽");

        int id;

        cartItems = context.getSharedPreferences("ITEMS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json;
        for (int i=1; i<20; i++){
            if(cartItems.contains("item "+i)){
                json = cartItems.getString("item " +i, "default");
                Type type = new TypeToken<List<String>>(){}.getType();
                List<String> arrPackageData = gson.fromJson(json, type);
                ArrayList<String> newItem = new ArrayList<>(arrPackageData);
                id = Integer.parseInt(newItem.get(0))-1;
                if(id == position) {
                    addedItems[position] = true;
                    break;
                }
            }
        }

        pressButton(position, _holder, currentItem);
        _holder.buttonCatalogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    pressButton(position, _holder, currentItem);
//                arrPackage = new ArrayList<>();
//                if(!isButtonPressed) {
//                    addedItems[position] = true;
//                    pressButton(position, _holder, currentItem);
//
//                    arrPackage.add(String.valueOf(currentItem.getId()));
//                    arrPackage.add(String.valueOf(currentItem.getCategory()));
//                    arrPackage.add(String.valueOf(currentItem.getName()));
//                    arrPackage.add(String.valueOf(currentItem.getDescription()));
//                    arrPackage.add(String.valueOf(currentItem.getPrice()));
//                    arrPackage.add(String.valueOf(currentItem.getTimeResult()));
//                    arrPackage.add(String.valueOf(currentItem.getPreparation()));
//                    arrPackage.add(String.valueOf(currentItem.getBio()));
//                    arrPackage.add(String.valueOf(currentItem.getPatients()));
//
//                    Gson gson = new Gson();
//                    String json = gson.toJson(arrPackage);
//                    SharedPreferences.Editor editor = cartItems.edit();
//                    editor.putString("item " + currentItem.getId(), json);
//                    editor.commit();
//                }
//                else{
//                    addedItems[position] = false;
//                    pressButton(position, _holder, currentItem);
//
//                    SharedPreferences.Editor editor = cartItems.edit();
//                    editor.remove("item " + currentItem.getId());
//                    editor.commit();
//                }
//                isButtonPressed = !isButtonPressed;

                SharedPreferences cartItems;
                cartItems = context.getSharedPreferences("ITEMS", MODE_PRIVATE);
                boolean isCartEmpty = true;
                double totalPrice = 0;
                Gson gson = new Gson();
                String json;
                for (int i=1; i<100; i++){
                    if(cartItems.contains("item "+i)){
                        json = cartItems.getString("item " +i, "default");
                        Type type = new TypeToken<List<String>>(){}.getType();
                        List<String> arrPackageData = gson.fromJson(json, type);
                        ArrayList<String> newItem = new ArrayList<>(arrPackageData);
                        totalPrice += Double.parseDouble(String.valueOf(newItem.get(4)));
                        isCartEmpty = false;
                    }
                }
                if(!isCartEmpty) {
                    txPrice.setText(totalPrice + " ₽");
                    cartWidget.setVisibility(View.VISIBLE);
                }
                else{
                    cartWidget.setVisibility(View.GONE);
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
