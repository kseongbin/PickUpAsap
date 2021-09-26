package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopInfoActivity extends AppCompatActivity {

    ArrayList<ShopInfo_Item> items = new ArrayList<>();
    ListView listView;
    ShopInfoAdapter adapter;
    boolean i = false;

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

    public void clickMap(View view) {
        Intent intent = new Intent(this, GoogleMapActivity.class);
        startActivity(intent);
    }

    public void clickFav(View view) {
        ImageView fav = findViewById(R.id.iv_fav);
        if (i == false){
            FirebaseMessaging.getInstance().subscribeToTopic("haengdang").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                    i = true;
                    Toast.makeText(ShopInfoActivity.this, "음식점의 업데이트를 알림으로 받습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            i = false;
            Toast.makeText(this, "음식점의 업데이트 알림을 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}