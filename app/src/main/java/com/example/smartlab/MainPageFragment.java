package com.example.smartlab;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartlab.adapters.CatalogAdapter;
import com.example.smartlab.adapters.CategoryAdapter;
import com.example.smartlab.adapters.NewsAdapter;
import com.example.smartlab.fragments.CartFragment;
import com.example.smartlab.items.CatalogItem;
import com.example.smartlab.items.CategoryItem;
import com.example.smartlab.items.NewsItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<NewsItem> newsItemList;
    ArrayList<CatalogItem> catalogItemList;
    ArrayList<CategoryItem> categoryItemList;

    JSONArray array;
    private List<Object> categoriesList = new ArrayList<>();
    private List<Object> newsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
    public void Inflate (View view){
        RecyclerView categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(new CategoryAdapter(getContext(), categoriesList));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        new GetCategories().execute(new JSONObject());
        new GetNews().execute(new JSONObject());

        initData();

        //новости кароч
        RecyclerView newsRecyclerView = view.findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false));
        newsRecyclerView.setAdapter(new NewsAdapter(getContext(), newsList));

        //категории
        RecyclerView categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(new CategoryAdapter(getContext(), categoriesList));

        //каталог
        CatalogAdapter.OnCatalogClickListener catalogClickListener = new CatalogAdapter.OnCatalogClickListener() {
            @Override
            public void onCatalogClick(CatalogItem catalogItem, int position) {
                createDialog(catalogItem);
            }
        };
        RecyclerView catalogRecyclerView = view.findViewById(R.id.catalogRecycleView);
        CatalogAdapter catalogAdapter = new CatalogAdapter(catalogItemList, catalogClickListener);
        catalogRecyclerView.setAdapter(catalogAdapter);
        catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false));
        return view;
    }
    private void addCategoriesFromJSON() {
        try {
            // Заполняем Модель спаршенными данными
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
            // Заполняем Модель спаршенными данными
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);
                int id = itemObj.getInt("id");
                String title = itemObj.getString("name");
                String description = itemObj.getString("description");
                String price = itemObj.getString("price");
                NewsItem categoryItem = new NewsItem(id,title, description, price, R.drawable.men);
                newsList.add(categoryItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void initData(){
        newsItemList = new ArrayList<>();
        categoryItemList = new ArrayList<>();
        catalogItemList = new ArrayList<>();

        /*newsItemList.add(new NewsItem("Чек-ап для мужчин", "9 исследований", 8000));
        newsItemList.add(new NewsItem("Подготовка к вакцинации", "Комплексное обследование перед вакцинацией", 4000));
        newsItemList.add(new NewsItem("назв", "камент", 10.0));
        newsItemList.add(new NewsItem("назв", "камент", 10.0));*/

        /*categoryItemList.add(new CategoryItem(1, "Популярные"));
        categoryItemList.add(new CategoryItem(2, "Covid"));
        categoryItemList.add(new CategoryItem(3, "Комплексные"));
        categoryItemList.add(new CategoryItem(4, "ЗОЖ"));*/

        catalogItemList.add(new CatalogItem(1, "ПЦР-тест на определение РНК коронавируса стандартный", "ну да описание", 1800, "2 дня", "подготовка", "Венозная кровь"));
        catalogItemList.add(new CatalogItem(1, "Клинический анализ крови с лейкоцитарной формулировкой", "des", 2000, "1 день", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "Биохимический анализ крови, базовый", "des", 2440, "1 день", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "СОЭ (венозная кровь)", "des", 1800, "1 день", "prep", "bio"));
        catalogItemList.add(new CatalogItem(1, "name", "des", 10.0, "1 d", "prep", "bio"));
    }
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
        add.setText("Добавить за " + String.valueOf(catalogItem.getPrice()) + " ₽");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentContainerView fragmentContainerView = view.findViewById(R.id.fragmentCartContainer);
                CartFragment cartFragment = new CartFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.fragmentCartContainer, cartFragment);
                transaction.commit();
                bottomSheetDialog.dismiss();
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