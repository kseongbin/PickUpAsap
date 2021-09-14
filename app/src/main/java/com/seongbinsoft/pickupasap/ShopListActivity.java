package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    ArrayList<ShopList_Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    ShopListAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        //툴바세팅
        toolbar = findViewById(R.id.toolbar_shoplist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items.add(new ShopList_Item("마포갈매기", "장사시간 : 오전 11:00 ~ 오후 09:00", R.drawable.paris));
        items.add(new ShopList_Item("홍콩반점", "장사시간 : 오전 10:00 ~ 오후 10:00", R.drawable.paris));
        items.add(new ShopList_Item("하남돼지집", "장사시간 : 오전 09:00 ~ 오후 09:00", R.drawable.paris));
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ShopListAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}