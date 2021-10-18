package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Receipt_Fragment extends Fragment {

    ListView listView;
    RecieptAdapter adapter;
    ArrayList<Receipt_Item> items = new ArrayList<>();
    AdView adView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_receipt_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull @NotNull InitializationStatus initializationStatus) {
                //Toast.makeText(getContext(), "이니셜 완료!", Toast.LENGTH_SHORT).show();
            }
        });
        adView = view.findViewById(R.id.adv);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //items.add(new Receipt_Item("09/02(목)", "하남돼지집 행당점", "삼겹살 구이(2인분) - 25,000원(30% ↓)", "배달 완료", R.drawable.newyork));
        //items.add(new Receipt_Item("09/03(금)", "홍콩반점 행당점", "짜장면(2인분) - 15,000원(30% ↓)", "접수 완료", R.drawable.newyork));
        //Toast.makeText(getContext(), ""+items.size(), Toast.LENGTH_SHORT).show();

        listView = view.findViewById(R.id.receipt_listview);
        adapter = new RecieptAdapter(getContext(), items);
        listView.setAdapter(adapter);

        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<Receipt_Item>> call = retrofitService.getReceiptArray();
        call.enqueue(new Callback<ArrayList<Receipt_Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Receipt_Item>> call, Response<ArrayList<Receipt_Item>> response) {
                ArrayList<Receipt_Item> retrofititems = response.body();
                if(items!=null){
                    items.clear();
                    adapter.notifyDataSetChanged();
                    items.addAll(retrofititems);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Receipt_Item>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Receipt_Item item = items.get(position);
                Intent intent = new Intent(getContext(), ShopInfoActivity.class);
                intent.putExtra("name", item.name);
                intent.putExtra("img", item.img);
                intent.putExtra("latitude", item.latitude);
                intent.putExtra("longitude", item.longitude);
                Gson gson = new Gson();
                String menujson = gson.toJson(item.menu);
                intent.putExtra("menu", menujson);
                startActivity(intent);
            }
        });
    }//onViewCreated method...
}