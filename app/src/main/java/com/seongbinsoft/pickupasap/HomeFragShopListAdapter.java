package com.seongbinsoft.pickupasap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class HomeFragShopListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ShopListActivity> items;

    public HomeFragShopListAdapter(Context context, ArrayList<ShopListActivity> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.activity_shop_list, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.listAdapter.notify();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        RecyclerView Home_Update_recyclerView;
        ShopListAdapter listAdapter;

        public VH(@NonNull View itemView) {
            super(itemView);

           Home_Update_recyclerView = itemView.findViewById(R.id.recyclerview);
           listAdapter = new ShopListAdapter(context, listAdapter.items);
        }
    }
}
