package com.seongbinsoft.pickupasap;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class HomeFragShopListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ShopList_Item> items;

    public HomeFragShopListAdapter(Context context, ArrayList<ShopList_Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_recyclerview_shoplist, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        ShopList_Item item = items.get(position);
        vh.name.setText(item.name);
        vh.msg.setText(item.msg);
        Glide.with(context).load(item.img).into(vh.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView name;
        TextView msg;
        ImageView img;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            msg = itemView.findViewById(R.id.tv_msg);
            img = itemView.findViewById(R.id.iv_img);

        }
    }

}
