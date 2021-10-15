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
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<MenuList_Item> items;
    ShopInfoAdapter adapter;
    boolean i = false;
    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        //items.add(new MenuList_Item("메뉴이름","(12개)","1000원","30%","700원","http://kim940840.dothome.co.kr/PickUpAsap/steak.jpg"));

        iv = findViewById(R.id.shopmenu_iv);
        tv = findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");

        String menu = intent.getStringExtra("menu");
//        Toast.makeText(this, ""+menu, Toast.LENGTH_LONG).show();
        Gson gson = new Gson();
        MenuList_Item[] arr = gson.fromJson(menu, MenuList_Item[].class);
        items = new ArrayList<MenuList_Item>(Arrays.asList(arr));
//        Toast.makeText(this, ""+items.size(), Toast.LENGTH_SHORT).show();


        tv.setText(name);
        Glide.with(this).load(img).into(iv);

        listView = findViewById(R.id.menuinfo_listview);
        adapter = new ShopInfoAdapter(this, items);
        listView.setAdapter(adapter);

        //화면 전환효과(Animation)
        iv.setTransitionName("img");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuList_Item item = items.get(position);
                Intent intent1 = new Intent(ShopInfoActivity.this, ShopMenuActivity.class);
                intent1.putExtra("name", item.menuName);
                intent1.putExtra("img", item.iv);
                startActivity(intent1);
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
            fav.setImageResource(R.drawable.ic_baseline_favorite_24);
            i = true;
            FirebaseMessaging.getInstance().subscribeToTopic("haengdang").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    Toast.makeText(ShopInfoActivity.this, "음식점의 업데이트를 알림으로 받습니다.", Toast.LENGTH_SHORT).show();
                }//onComplete method...
            });//FirebaseMessaging method..
        }else {
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            i = false;
            Toast.makeText(this, "음식점의 업데이트 알림을 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }//clickFav method..
}