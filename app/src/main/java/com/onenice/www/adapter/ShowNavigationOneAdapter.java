package com.onenice.www.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onenice.www.R;
import com.onenice.www.bean.ShowNavigationMsgBean;
import com.onenice.www.bean.ShowNavigationOneBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ShowNavigationOneAdapter extends RecyclerView.Adapter<ShowNavigationOneAdapter.ViewHolder> {
    private List<ShowNavigationOneBean.ResultBean> list;
    private Context context;
    private List<Boolean> isClick = new ArrayList<>();
    public ShowNavigationOneAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ShowNavigationOneBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_navigation_one, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //给isClick集合添加数据
        if (list.size() > 0) {
            for (int y = 0; y < list.size(); y++) {
                isClick.add(false);
            }
        }
        viewHolder.getdata(list.get(i),isClick,context,i);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item_one_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_one_name=itemView.findViewById(R.id.item_one_name);
        }

        public void getdata(final ShowNavigationOneBean.ResultBean resultBean, final List<Boolean> isClick, Context context, final int i) {
            item_one_name.setText(resultBean.getName());
            item_one_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //判断集合是否为空，集合是否有内容（集合为空表示没有 new 这个集合对象）


                    item_one_name.setTextColor(Color.parseColor("#ff5f71"));
                    EventBus.getDefault().post(new ShowNavigationMsgBean(resultBean.getId(),"one"));
                }
            });
        }
    }

}
