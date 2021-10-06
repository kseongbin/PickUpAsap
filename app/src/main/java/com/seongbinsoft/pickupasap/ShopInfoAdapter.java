package com.seongbinsoft.pickupasap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopInfoAdapter extends BaseAdapter {

    Context context;
    ArrayList<ShopInfo_Item> items;

    public ShopInfoAdapter(Context context, ArrayList<ShopInfo_Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {return items.size();}

    @Override
    public Object getItem(int position) {return items.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_listview_shopinfo, parent, false);
        }

        TextView name = convertView.findViewById(R.id.tv_menuname);
        TextView remain = convertView.findViewById(R.id.tv_remain);
        TextView before = convertView.findViewById(R.id.tv_beforeprice);
        TextView dc = convertView.findViewById(R.id.tv_dc);
        TextView after = convertView.findViewById(R.id.tv_afterprice);
        ImageView iv = convertView.findViewById(R.id.iv_menuinfo);

        ShopInfo_Item item = items.get(position);
        name.setText(item.name);
        remain.setText(item.remain);
        before.setText(item.before);
        dc.setText(item.dc);
        after.setText(item.after);
        Glide.with(context).load(item.iv).into(iv);
        //Log.i("URL", item.iv);

        return convertView;
    }
}
