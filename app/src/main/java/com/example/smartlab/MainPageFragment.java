package com.example.smartlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlab.activities.CartActivity;
import com.example.smartlab.adapters.CatalogAdapter;
import com.example.smartlab.adapters.CategoryAdapter;
import com.example.smartlab.adapters.NewsAdapter;
import com.example.smartlab.items.CatalogItem;
import com.example.smartlab.items.CategoryItem;
import com.example.smartlab.items.NewsItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {
    public MainPageFragment() {
        // Required empty public constructor
    }
    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    static JSONArray array;
    private List<Object> categoriesList = new ArrayList<>();
    private List<Object> newsList = new ArrayList<>();
    static private List<Object> catalogList = new ArrayList<>();
    static private List<CatalogItem> catalogItemList = new ArrayList<>();
    TextView tx;
    LinearLayout lr;
    ConstraintLayout cartWidget;
    static CatalogAdapter catalogAdapter;

    static boolean[] catalogCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        initData();
        initCart();
    }
    private class GetCategories extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.0.2.2:8000/api/category/");
                    connection = (HttpURLConnection) url.openConnection();
                    // Выбираем метод GET для запроса
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();
                    // Полученный результат разбиваем с помощью байтовых потоков
                    stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    JSONObject root = new JSONObject(buf.toString());
                    array= root.getJSONArray("results");
                    addCategoriesFromJSON();
                    return (buf.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } return null;
        }
    }
    private class GetNews extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.0.2.2:8000/api/news/");
                    connection = (HttpURLConnection) url.openConnection();
                    // Выбираем метод GET для запроса
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();
                    // Полученный результат разбиваем с помощью байтовых потоков
                    stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    JSONObject root = new JSONObject(buf.toString());
                    array= root.getJSONArray("results");
                    addNewsFromJSON();
                    return (buf.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } return null;
        }
    }
    private class GetCatalog extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.0.2.2:8000/api/catalog/");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();

                    stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    JSONObject root = new JSONObject(buf.toString());
                    array= root.getJSONArray("results");
                    addCatalogFromJSON(1);
                    return (buf.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } return null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        lr = view.findViewById(R.id.linearLayout);
        RecyclerView recyclerView = view.findViewById(R.id.newsRecyclerView);
        CoordinatorLayout constraintLayout = view.findViewById(R.id.scrollView2);

        new GetCategories().execute(new JSONObject());
        new GetNews().execute(new JSONObject());
        new GetCatalog().execute(new JSONObject());

        cartWidget = view.findViewById(R.id.cartWidget);
        cartWidget.setVisibility(View.GONE);
        tx = view.findViewById(R.id.textPriceToCart);

        ConstraintLayout btnGoToCart = view.findViewById(R.id.btnGoToCart);
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CartActivity.class));
            }
        });
        return view;
    }
    public void initData(){
        //новости кароч
        RecyclerView newsRecyclerView = getView().findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false));
        newsRecyclerView.setAdapter(new NewsAdapter(getContext(), newsList));

        //категории
        RecyclerView categoryRecyclerView = getView().findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(new CategoryAdapter(getContext(), categoriesList));

        //каталог
        RecyclerView catalogRecyclerView = getView().findViewById(R.id.catalogRecycleView);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false));
         catalogAdapter = new CatalogAdapter(getContext(), catalogList,cartWidget, tx, catalogCount, new CatalogAdapter.OnCatalogClickListener() {
            @Override
            public void onCatalogClick(CatalogItem catalogItem, int position) { createDialog(catalogItem); }
        });
        catalogRecyclerView.setAdapter(catalogAdapter);
    }
    public void initCart(){
        SharedPreferences cartItems;
        cartItems = getActivity().getApplicationContext().getSharedPreferences("ITEMS", getContext().MODE_PRIVATE);
        boolean isCartEmpty = true;
        double totalPrice = 0;
        Gson gson = new Gson();
        String json;
        for (int i=1; i<20; i++){
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
            tx.setText(totalPrice + " ₽");
            cartWidget.setVisibility(View.VISIBLE);
        }
        else{
            cartWidget.setVisibility(View.GONE);
        }
    }
    private void addCategoriesFromJSON() {
        try {
            categoriesList.clear();
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);
                int id = itemObj.getInt("id");
                String title = itemObj.getString("name");
                CategoryItem categoryItem = new CategoryItem(id,title);
                categoriesList.add(categoryItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void addNewsFromJSON() {
        try {
            newsList.clear();
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);
                int id = itemObj.getInt("id");
                String title = itemObj.getString("name");
                String description = itemObj.getString("description");
                String price = itemObj.getString("price");
                String image = itemObj.getString("image");
                NewsItem newsItem = new NewsItem(id,title, description, price, image);
                newsList.add(newsItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void addCatalogFromJSON(int category_id) {
        try {
            catalogList.clear();
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);
                    int id = itemObj.getInt("id");
                    int category = itemObj.getInt("category");
                    String name = itemObj.getString("name");
                    String description = itemObj.getString("description");
                    String price = itemObj.getString("price");
                    String timeResult = itemObj.getString("time_result");
                    String preparation = itemObj.getString("preparation");
                    String bio = itemObj.getString("bio");
                    CatalogItem catalogItem = new CatalogItem(id, category, name, description, price, timeResult, preparation, bio, 1);
                    catalogList.add(catalogItem);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catalogCount = new boolean[catalogList.size()];
        catalogAdapter.notifyDataSetChanged();
    }
    //TODO диалог
    public void createDialog(CatalogItem catalogItem){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.dialog_add_to_cart);
        ImageView dismiss = bottomSheetDialog.findViewById(R.id.btnAddToCartClose);
        TextView title = bottomSheetDialog.findViewById(R.id.textAddToCartTitle);
        title.setText(catalogItem.getName());
        TextView description = bottomSheetDialog.findViewById(R.id.textAddToCartDescription);
        description.setText(catalogItem.getDescription());
        TextView preparation = bottomSheetDialog.findViewById(R.id.textAddToCartPreparation);
        preparation.setText(catalogItem.getPreparation());
        TextView time = bottomSheetDialog.findViewById(R.id.textAddToCartResultField);
        time.setText(catalogItem.getTimeResult());
        TextView bio = bottomSheetDialog.findViewById(R.id.textAddToCartBioField);
        bio.setText(catalogItem.getBio());
        Button add = bottomSheetDialog.findViewById(R.id.btnAddressConfirm);
        add.setText("Добавить за " + catalogItem.getPrice() + " ₽");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Пожалуйста не нажимайте...", Toast.LENGTH_SHORT).show();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
}