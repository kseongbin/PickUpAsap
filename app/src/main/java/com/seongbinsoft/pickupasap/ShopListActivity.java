package com.seongbinsoft.pickupasap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    ArrayList<ShopList_Item> items = new ArrayList<>();

    RecyclerView recyclerView;
    ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        items.add(new ShopList_Item("마포갈매기", "장사시간 : 오전 11:00 ~ 오후 09:00", R.drawable.paris));
        items.add(new ShopList_Item("홍콩반점", "장사시간 : 오전 10:00 ~ 오후 10:00", R.drawable.paris));
        items.add(new ShopList_Item("하남돼지집", "장사시간 : 오전 09:00 ~ 오후 09:00", R.drawable.paris));
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ShopListAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

}