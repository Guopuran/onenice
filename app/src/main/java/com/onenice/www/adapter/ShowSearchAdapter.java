package com.onenice.www.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.onenice.www.R;
import com.onenice.www.bean.ShowSearchBean;


import java.util.ArrayList;
import java.util.List;

public class ShowSearchAdapter extends RecyclerView.Adapter<ShowSearchAdapter.ViewHolder> {
    private List<ShowSearchBean.ResultBean> list;
    private Context context;

    public ShowSearchAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ShowSearchBean.ResultBean> mlist) {
        list.clear();
        if (mlist!=null||mlist.size()!=0){
            list.addAll(mlist);
            notifyDataSetChanged();
        }

    }

    public void addList(List<ShowSearchBean.ResultBean> mlist) {
        if (mlist!=null||mlist.size()!=0){
            list.addAll(mlist);
            notifyDataSetChanged();
        }

    }
    public ShowSearchBean.ResultBean getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xrecy_item_shop, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.getdata(getItem(i),context,i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView item_image;
        private TextView item_name;
        private TextView item_price;
        private TextView item_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image=itemView.findViewById(R.id.item_classify_image);
            item_name=itemView.findViewById(R.id.item_classify_name);
            item_price=itemView.findViewById(R.id.item_classify_price);
            item_num=itemView.findViewById(R.id.item_classify_num);
        }

        public void getdata(ShowSearchBean.ResultBean item, Context context, int i) {
            item_image.setImageURI(Uri.parse(item.getMasterPic()));
            item_name.setText(item.getCommodityName());
            item_price.setText("￥"+item.getPrice());
            item_num.setText("已售"+item.getSaleNum()+"件");
        }
    }
}
