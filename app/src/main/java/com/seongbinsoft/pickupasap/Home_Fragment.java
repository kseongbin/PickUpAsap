package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_Fragment extends Fragment {

    RecyclerView rv_Update, rv_Before;
    ArrayList<ShopList_Item> items = new ArrayList<>();
    ArrayList<HomeFragHorizon_Item> hori_items = new ArrayList<>();
    HomeFragShopListAdapter adapter;
    HomeFragAdapterHorizon horizon_adapter;
    SwipeRefreshLayout mySwipeRefreshLayout;

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
//        if (hori_items!=null){
//            hori_items.add(new HomeFragHorizon_Item(R.drawable.newyork, "식당이름", "메뉴이름", "2만원 →", "1만4000원(30%↓)"));
//            hori_items.add(new HomeFragHorizon_Item(R.drawable.sydney, "식당이름", "메뉴이름", "2만원 →", "1만4000원(30%↓)"));
//            hori_items.add(new HomeFragHorizon_Item(R.drawable.paris, "식당이름", "메뉴이름", "2만원 →", "1만4000원(30%↓)"));
//            Toast.makeText(getContext(), ""+hori_items.size(), Toast.LENGTH_SHORT).show();
//        }
        load();
        rv_Update = view.findViewById(R.id.rv_update);
        horizon_adapter = new HomeFragAdapterHorizon(getActivity(), hori_items);
        rv_Update.setAdapter(horizon_adapter);


        //Vertical RecyclerView
        load2();
        rv_Before = view.findViewById(R.id.rv_before);
        adapter = new HomeFragShopListAdapter(getActivity(), items);
        rv_Before.setAdapter(adapter);

        //새로고침
        mySwipeRefreshLayout = view.findViewById(R.id.swiper);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
                load2();
                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

    }//OnViewCreated method...

    public void load(){
        RetrofitService retrofitServicehori = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<HomeFragHorizon_Item>> callhori = retrofitServicehori.gethoriArray();
        callhori.enqueue(new Callback<ArrayList<HomeFragHorizon_Item>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeFragHorizon_Item>> call, Response<ArrayList<HomeFragHorizon_Item>> response) {
                ArrayList<HomeFragHorizon_Item> retrofititemshori = response.body();
                if (hori_items!=null){
                    hori_items.clear();
                    horizon_adapter.notifyDataSetChanged();
                    hori_items.addAll(retrofititemshori);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<HomeFragHorizon_Item>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void load2(){
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<ShopList_Item>> call = retrofitService.getShopArray();

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
                Toast.makeText(getContext(), "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("TAG", t.getMessage());
            }
        });
    }
}//Home_Fragment...