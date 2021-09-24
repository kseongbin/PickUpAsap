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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {

    RecyclerView rv_Update, rv_Before;
    ArrayList<ShopList_Item> items = new ArrayList<>();
    HomeFragShopListAdapter adapter;

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

        //JsonArray 가져오기
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<RetrofitItem>> call = (Call)retrofitService.getShopArray();

        call.enqueue(new Callback<ArrayList<RetrofitItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RetrofitItem>> call, Response<ArrayList<RetrofitItem>> response) {
                ArrayList<RetrofitItem> retrofititems = response.body();

            }

            @Override
            public void onFailure(Call<ArrayList<RetrofitItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Horizontal RecyclerView
        if (items!=null){
            items.add(new ShopList_Item("춘천닭갈비", "장사시간 : 오전 11:00 ~ 오후 09:00", R.drawable.paris));
            items.add(new ShopList_Item("뉴욕스테이크", "장사시간 : 오전 10:00 ~ 오후 10:00", R.drawable.paris));
            items.add(new ShopList_Item("홍콩반점", "장사시간 : 오전 09:00 ~ 오후 09:00", R.drawable.paris));
        }
        rv_Update = view.findViewById(R.id.rv_update);
        adapter = new HomeFragShopListAdapter(getActivity(), items);
        rv_Update.setAdapter(adapter);

        //Vertical RecyclerView
        rv_Before = view.findViewById(R.id.rv_before);
        rv_Before.setAdapter(adapter);


    }
}