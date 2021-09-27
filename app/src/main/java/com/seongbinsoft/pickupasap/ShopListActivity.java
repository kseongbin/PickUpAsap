package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        //items.add(new ShopList_Item("마포갈매기", "장사시간 : 오전 11:00 ~ 오후 09:00", R.drawable.paris));
        //items.add(new ShopList_Item("홍콩반점", "장사시간 : 오전 10:00 ~ 오후 10:00", R.drawable.paris));
        //items.add(new ShopList_Item("하남돼지집", "장사시간 : 오전 09:00 ~ 오후 09:00", R.drawable.paris));
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ShopListAdapter(this, items);
        recyclerView.setAdapter(adapter);

        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<ShopList_Item>> call = (Call)retrofitService.getShopArray();

        call.enqueue(new Callback<ArrayList<ShopList_Item>>() {
            @Override
            public void onResponse(Call<ArrayList<ShopList_Item>> call, Response<ArrayList<ShopList_Item>> response) {
                ArrayList<ShopList_Item> retrofititems = response.body();
                if(items!=null){
                    items.clear();
                    adapter.notifyDataSetChanged();
                    items.addAll(retrofititems);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ShopList_Item>> call, Throwable t) {
                Toast.makeText(ShopListActivity.this, "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("TAG", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}