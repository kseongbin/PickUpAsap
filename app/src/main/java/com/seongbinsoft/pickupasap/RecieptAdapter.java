package com.seongbinsoft.pickupasap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return null;
    }
}
