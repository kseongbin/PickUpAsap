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

    ListView listView;
    boolean i = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);


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