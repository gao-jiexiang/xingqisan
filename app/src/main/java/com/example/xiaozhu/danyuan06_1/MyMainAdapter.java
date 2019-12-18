package com.example.xiaozhu.danyuan06_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by xiaozhu on 2019/10/30.
 */

public class MyMainAdapter extends RecyclerView.Adapter{

    private Context context;
    private ArrayList<ItemBean.DataBean> lists;

    public MyMainAdapter(Context context, ArrayList<ItemBean.DataBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            View view1 = LayoutInflater.from(context).inflate(R.layout.view1_layout, parent, false);
            ViewHoler1 viewHoler1 = new ViewHoler1(view1);
            return viewHoler1;
        }else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.view2_layout, parent, false);
            ViewHoler2 viewHoler2 = new ViewHoler2(view2);
            return viewHoler2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        ItemBean.DataBean data = lists.get(position);
        if(type==1){
            ViewHoler1 viewHoler1 = (ViewHoler1) holder;
            Glide.with(context).load(data.getPic()).into(viewHoler1.pic1);
            viewHoler1.num1.setText(data.getNum()+"");
            viewHoler1.title1.setText(data.getTitle());
            viewHoler1.food_str1.setText(data.getFood_str());
        }else {
            ViewHoler2 viewHoler2 = (ViewHoler2) holder;
            Glide.with(context).load(data.getPic()).into(viewHoler2.pic2);
            viewHoler2.num2.setText(data.getNum()+"");
            viewHoler2.title2.setText(data.getTitle());
            viewHoler2.food_str2.setText(data.getFood_str());
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onClick!=null){
                    onClick.onLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        ItemBean.DataBean data = lists.get(position);
        int num = data.getNum();
        if(num%2==0){
            return 1;
        }else {
            return 2;
        }
    }

    class ViewHoler1 extends RecyclerView.ViewHolder{
        ImageView pic1;
        TextView num1;
        TextView title1;
        TextView food_str1;
        public ViewHoler1(View itemView) {
            super(itemView);
            pic1 = itemView.findViewById(R.id.pic1);
            num1 = itemView.findViewById(R.id.num1);
            title1 = itemView.findViewById(R.id.title1);
            food_str1 = itemView.findViewById(R.id.food_str1);
        }
    }

    class ViewHoler2 extends RecyclerView.ViewHolder{
        ImageView pic2;
        TextView num2;
        TextView title2;
        TextView food_str2;
        public ViewHoler2(View itemView) {
            super(itemView);
            pic2 = itemView.findViewById(R.id.pic2);
            num2 = itemView.findViewById(R.id.num2);
            title2 = itemView.findViewById(R.id.title2);
            food_str2 = itemView.findViewById(R.id.food_str2);
        }
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    interface OnClick{
        void onLongClick(int a);
    }
}
