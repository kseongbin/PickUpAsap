package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Receipt_Fragment extends Fragment {

    ListView listView;
    RecieptAdapter adapter;
    ArrayList<Receipt_Item> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_receipt_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items.add(new Receipt_Item("09/02(목)", "하남돼지집 행당점", "삼겹살 구이(2인분) - 25,000원(30% ↓)", "배달 완료", R.drawable.newyork));
        items.add(new Receipt_Item("09/03(금)", "홍콩반점 행당점", "짜장면(2인분) - 15,000원(30% ↓)", "접수 완료", R.drawable.newyork));
        //Toast.makeText(getContext(), ""+items.size(), Toast.LENGTH_SHORT).show();

        listView = view.findViewById(R.id.receipt_listview);
        adapter = new RecieptAdapter(getContext(), items);
        listView.setAdapter(adapter);
    }
}