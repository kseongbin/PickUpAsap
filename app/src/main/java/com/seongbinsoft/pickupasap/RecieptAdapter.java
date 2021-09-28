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
            convertView = inflater.inflate(R.layout.item_listview_receipt, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Receipt_Item item = items.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder.date!=null){holder.date.setText(item.date);}
        if (holder.status!=null){holder.status.setText(item.status);}
        if (holder.name!=null){holder.name.setText(item.name);}
        if (holder.msg!=null){holder.msg.setText(item.msg);}
        Glide.with(context).load(item.img).into(holder.img);

        return convertView;
    }

    class ViewHolder{
        TextView date;
        TextView name;
        TextView msg;
        TextView status;
        ImageView img;

        public ViewHolder(View itemView){

            date = itemView.findViewById(R.id.tv_date);
            name = itemView.findViewById(R.id.tv_name);
            msg = itemView.findViewById(R.id.tv_menu);
            status = itemView.findViewById(R.id.tv_progress);
            img = itemView.findViewById(R.id.iv_receipt);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ShopInfoActivity.class);
//                    context.startActivity(intent);
//                }
//            });
        }
    }

}
