package com.example.xiaozhu.danyuan06_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by xiaozhu on 2019/10/30.
 */

public class ShowAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<SqlBean> lists;

    public ShowAdapter(Context context, ArrayList<SqlBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.show_lists_layout, null);
            holder = new ViewHolder();
            holder.pic = convertView.findViewById(R.id.pic);
            holder.num = convertView.findViewById(R.id.num);
            holder.title = convertView.findViewById(R.id.title);
            holder.food_str = convertView.findViewById(R.id.food_str);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        SqlBean sqlBean = lists.get(position);
        Glide.with(context).load(sqlBean.getPic()).into(holder.pic);
        holder.num.setText(sqlBean.getId()+"");
        holder.title.setText(sqlBean.getTitle());
        holder.food_str.setText(sqlBean.getFood_str());

        return convertView;
    }

    class ViewHolder{
        private ImageView pic;
        private TextView num;
        private TextView title;
        private TextView food_str;
    }
}
