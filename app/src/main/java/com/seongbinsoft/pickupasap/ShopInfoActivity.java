package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopInfoActivity extends AppCompatActivity {

    ArrayList<ShopInfo_Item> items = new ArrayList<>();
    ListView listView;
    ShopInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        items.add(new ShopInfo_Item("곱창전골", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.newyork));
        items.add(new ShopInfo_Item("한우 사골", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.paris));
        items.add(new ShopInfo_Item("버섯 육개장", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.sydney));
        items.add(new ShopInfo_Item("해장탕", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.paris));
        //Toast.makeText(this, ""+items.size(), Toast.LENGTH_SHORT).show();
        listView = findViewById(R.id.menuinfo_listview);
        adapter = new ShopInfoAdapter(this, items);
        listView.setAdapter(adapter);
    }

    public void clickBack(View view) {
        onBackPressed();
    }
}