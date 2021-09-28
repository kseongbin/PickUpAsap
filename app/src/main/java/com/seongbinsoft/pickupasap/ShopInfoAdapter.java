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
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        ShopInfo_Item item = items.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(item.name);
        holder.remain.setText(item.remain);
        holder.before.setText(item.before);
        holder.dc.setText(item.dc);
        holder.after.setText(item.after);
        Glide.with(context).load(item.iv).into(holder.iv);
        //Picasso.get().load(item.iv).into(holder.iv);
        Log.i("IV", item.iv);

        return convertView;
    }

    class ViewHolder{
        TextView name;
        TextView remain;
        TextView before;
        TextView dc;
        TextView after;
        ImageView iv;

        public ViewHolder(View itemView){
            name = itemView.findViewById(R.id.tv_menuname);
            remain = itemView.findViewById(R.id.tv_remain);
            before = itemView.findViewById(R.id.tv_beforeprice);
            dc = itemView.findViewById(R.id.tv_dc);
            after = itemView.findViewById(R.id.tv_afterprice);
            iv = itemView.findViewById(R.id.iv_menuinfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopMenuActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
