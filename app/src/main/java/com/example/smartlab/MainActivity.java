package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CatalogItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*initCatalog();
        CatalogAdapter adapter = new CatalogAdapter(this, list);
        ListView listCatalog= findViewById(R.id.catalogListView1);

        listCatalog.setAdapter(adapter);*/
    }
   /* private void initCatalog(){
        list = new ArrayList<CatalogItem>();
        list.add(new CatalogItem(1, "name", "des", 10.0, "1 d", "prep", "bio"));
        list.add(new CatalogItem(1, "name", "des", 10.0, "1 d", "prep", "bio"));
        list.add(new CatalogItem(1, "name", "des", 10.0, "1 d", "prep", "bio"));
    }*/
}