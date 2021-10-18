package com.seongbinsoft.pickupasap;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeFragAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<ShopList_Item> items;

    public HomeFragAdapter(Context context, ArrayList<ShopList_Item> items) {
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
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);
        Glide.with(context).load(item.img).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvName;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv_img);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    ShopList_Item item = items.get(position);
                    Intent intent = new Intent(context, ShopInfoActivity.class);
                    intent.putExtra("name", item.name);
                    intent.putExtra("img", item.img);
                    intent.putExtra("latitude", item.latitude);
                    intent.putExtra("longitude", item.longitude);
                    Gson gson = new Gson();
                    String menujson = gson.toJson(item.menu);
                    intent.putExtra("menu", menujson);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context, new Pair<View, String>(iv, "img"));
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
