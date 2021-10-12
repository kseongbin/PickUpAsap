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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragAdapterHorizon extends RecyclerView.Adapter {

    Context context;
    ArrayList<HomeFragHorizon_Item> items;

    public HomeFragAdapterHorizon(Context context, ArrayList<HomeFragHorizon_Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.item_recyclerview_homefraghorizon, parent, false);
        VH holder = new VH(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        HomeFragHorizon_Item item = items.get(position);
        Glide.with(context).load(item.horiimg).into(vh.horiiv);
        vh.horiname.setText(item.horiname);
        vh.horimenu.setText(item.horimenu);
        vh.one.setText(item.one);
        vh.horireal.setText(item.horireal);
        vh.horidc.setText(item.horidc);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView horiiv;
        TextView horiname;
        TextView horimenu;
        TextView one;
        TextView horireal;
        TextView horidc;

        public VH(@NonNull @NotNull View itemView) {
            super(itemView);

            horiiv = itemView.findViewById(R.id.iv_horiimg);
            horiname = itemView.findViewById(R.id.tv_shopnamehori);
            horimenu = itemView.findViewById(R.id.tv_shopmenuhori);
            one = itemView.findViewById(R.id.tv_one);
            horireal = itemView.findViewById(R.id.tv_realprice);
            horidc = itemView.findViewById(R.id.dc_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    HomeFragHorizon_Item item = items.get(position);
                    Intent intent = new Intent(context, ShopInfoActivity.class);
                    intent.putExtra("name", item.horiname);
                    intent.putExtra("img", item.horiimg);

                    Gson gson = new Gson();
                    String menujson = gson.toJson(item.menu);
                    intent.putExtra("menu", menujson);

                    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context, new Pair<View, String>(horiiv, "img"));
                        context.startActivity(intent, options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}
