package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoActivity extends AppCompatActivity {

    ArrayList<ShopInfo_Item> items = new ArrayList<>();
    ListView listView;
    ShopInfoAdapter adapter;
    boolean i = false;
    ImageView iv_img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        iv_img = findViewById(R.id.shopmenu_iv);
        tv = findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String name = getIntent().getStringExtra("name");
        String iv = getIntent().getStringExtra("img");
        tv.setText(name);
        Glide.with(ShopInfoActivity.this).load(iv).into(iv_img);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            iv_img.setTransitionName("img");

//        items.add(new ShopInfo_Item("곱창전골", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.newyork));
//        items.add(new ShopInfo_Item("한우 사골", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.paris));
//        items.add(new ShopInfo_Item("버섯 육개장", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.sydney));
//        items.add(new ShopInfo_Item("해장탕", "(남은갯수: 10개)", "20,000원", "30% ↓", "14,000원", R.drawable.paris));
        //Toast.makeText(this, ""+items.size(), Toast.LENGTH_SHORT).show();
        listView = findViewById(R.id.menuinfo_listview);
        adapter = new ShopInfoAdapter(this, items);
        listView.setAdapter(adapter);

        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<ShopInfo_Item>> call = retrofitService.getMenuArray();
        call.enqueue(new Callback<ArrayList<ShopInfo_Item>>() {
            @Override
            public void onResponse(Call<ArrayList<ShopInfo_Item>> call, Response<ArrayList<ShopInfo_Item>> response) {
                ArrayList<ShopInfo_Item> retrofititems = response.body();
                if (items!=null){
                    items.clear();
                    adapter.notifyDataSetChanged();
                    items.addAll(retrofititems);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ShopInfo_Item>> call, Throwable t) {
                Toast.makeText(ShopInfoActivity.this, "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopInfo_Item item = items.get(position);
                Intent intent = new Intent(ShopInfoActivity.this, ShopMenuActivity.class);
                intent.putExtra("name", item.name);
                intent.putExtra("img",item.iv);
                startActivity(intent);
            }
        });
    }//onCreate method...

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