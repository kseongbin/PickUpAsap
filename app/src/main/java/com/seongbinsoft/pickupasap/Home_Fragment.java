package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Fragment extends Fragment {

    RecyclerView rv_Update;
    ArrayList<ShopList_Item> items = new ArrayList<>();
    HomeFragShopListAdapter horizontalAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //한식 써클이미지뷰 들어가기
        CircleImageView civ = view.findViewById(R.id.civ01);
        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopListActivity.class);
                startActivity(intent);
            }
        });

        //Horizontal RecyclerView
        if (items!=null){
            items.add(new ShopList_Item("마포갈매기", "장사시간 : 오전 11:00 ~ 오후 09:00", R.drawable.paris));
            items.add(new ShopList_Item("홍콩반점", "장사시간 : 오전 10:00 ~ 오후 10:00", R.drawable.paris));
            items.add(new ShopList_Item("하남돼지집", "장사시간 : 오전 09:00 ~ 오후 09:00", R.drawable.paris));
        }
        rv_Update = view.findViewById(R.id.rv_update);
        horizontalAdapter = new HomeFragShopListAdapter(getActivity(), items);
        rv_Update.setAdapter(horizontalAdapter);


    }
}