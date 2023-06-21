package com.example.smartlab.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartlab.R;
import com.example.smartlab.items.CatalogItem;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartCatalogAdapter extends RecyclerView.Adapter<CartCatalogAdapter.ViewHolder>{

    SharedPreferences cartItems;
    private final Context context;
    TextView txPrice;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textCatalogName, textCatalogPatient, textCatalogPrice;
        public ImageView btnPlus, btnMinus;
        public ImageButton btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCatalogName = itemView.findViewById(R.id.textCartTitle);
            textCatalogPatient = itemView.findViewById(R.id.textCartPatient);
            textCatalogPrice = itemView.findViewById(R.id.textCartPrice);
            btnRemove = itemView.findViewById(R.id.btnCartRemoveItem);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
    ArrayList<CatalogItem> list;
    public CartCatalogAdapter(Context context, TextView txPrice, ArrayList<CatalogItem> list) {
        this.context = context;
        this.txPrice = txPrice;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CatalogItem currentItem = list.get(position);
        TextView textCatalogName = holder.textCatalogName;
        TextView textCatalogPrice = holder.textCatalogPrice;
        TextView textCatalogPatient = holder.textCatalogPatient;

        textCatalogName.setText(currentItem.getName());
        textCatalogPrice.setText(currentItem.getPrice()+" ₽");
        textCatalogPatient.setText(checkPatients(currentItem.getPatients()));

        cartItems = context.getSharedPreferences("ITEMS", MODE_PRIVATE);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = cartItems.edit();
                editor.remove("item " + currentItem.getId());
                editor.commit();

                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());

                double sum = 0;
                for(int i = 0; i < list.size(); i++){
                    sum += Double.parseDouble(String.valueOf(list.get(i).getPrice()));
                }
                txPrice.setText(sum + " ₽");
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentItem.getPatients() > 1){
                    int patients = currentItem.getPatients();
                    patients--;
                    currentItem.setPatients(patients);
                    holder.textCatalogPatient.setText(checkPatients(patients));

                    ArrayList<String> arrPackage = new ArrayList<>();

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
                }
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int patients = currentItem.getPatients();
                patients++;
                currentItem.setPatients(patients);
                holder.textCatalogPatient.setText(checkPatients(patients));

                ArrayList<String> arrPackage = new ArrayList<>();

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
            }
        });
    }
    public String checkPatients(int count){
        if((count % 10 == 0) || (count >= 10 && count <= 20) || (count % 10 >= 5)) return count + " пациентов";
        else if(count % 10 >= 2 && count % 10 <= 4) return count + " пациента";
        return count + " пациент";
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
