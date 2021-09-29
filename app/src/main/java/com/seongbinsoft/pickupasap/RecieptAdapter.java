package com.seongbinsoft.pickupasap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecieptAdapter extends BaseAdapter {

    Context context;
    ArrayList<Receipt_Item> items;

    public RecieptAdapter(Context context, ArrayList<Receipt_Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_listview_receipt, null);
        }
        TextView date = convertView.findViewById(R.id.tv_date);
        TextView name = convertView.findViewById(R.id.tv_name);
        TextView msg = convertView.findViewById(R.id.tv_menu);
        TextView status = convertView.findViewById(R.id.tv_progress);
        ImageView iv = convertView.findViewById(R.id.iv_receipt);

        Receipt_Item item = items.get(position);
        date.setText(item.date);
        name.setText(item.name);
        msg.setText(item.msg);
        status.setText(item.status);
        Glide.with(context).load(item.img).into(iv);

        return convertView;
    }
}
