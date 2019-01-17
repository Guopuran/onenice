package com.onenice.www.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.onenice.www.R;
import com.onenice.www.adapter.FootAdapter;
import com.onenice.www.base.BaseActivity;
import com.onenice.www.bean.FootBean;
import com.onenice.www.presenter.IpresenterImpl;
import com.onenice.www.util.Apis;
import com.onenice.www.view.IView;

public class FootActivity extends BaseActivity implements IView {
    private IpresenterImpl mIpresenterImpl;
    private XRecyclerView foot_xrecy;
    private final int COUNT_ITEM = 2;
    private final int COUNT = 5;
    private int page;
    private FootAdapter footAdapter;
    private  boolean foot_flag;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_foot;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //获取资源ID
        foot_xrecy = findViewById(R.id.foot_xrecy);
        //互绑
        initPreseter();
    }

    @Override
    protected void initData() {
        initRecy();
    }

    private void initRecy() {
        page=1;
        //布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,COUNT_ITEM);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        foot_xrecy.setLayoutManager(gridLayoutManager);
        //允许刷新和加载
        foot_xrecy.setLoadingMoreEnabled(true);
        foot_xrecy.setPullRefreshEnabled(true);
        footAdapter = new FootAdapter(this);
        foot_xrecy.setAdapter(footAdapter);
        foot_xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initUrl(page);
            }

            @Override
            public void onLoadMore() {
                if (foot_flag){
                    foot_xrecy.loadMoreComplete();
                }else{
                    foot_xrecy.setLoadingMoreEnabled(true);
                }
                initUrl(page);
            }
        });
        initUrl(page);
    }

    private void initUrl(int page) {
        mIpresenterImpl.getRequestIpresenter(String.format(Apis.SHOW_FOOT_SHOP_URL,page,COUNT),FootBean.class);
    }

    private void initPreseter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        mIpresenterImpl.deatch();
    }

    @Override
    public void success(Object object) {
        if (object instanceof FootBean){
            FootBean footBean= (FootBean) object;
            if (page==1){
                footAdapter.setList(footBean.getResult());
            }else{
                footAdapter.addList(footBean.getResult());
            }
//停止刷新加载
            foot_xrecy.refreshComplete();
            foot_xrecy.loadMoreComplete();
            page++;
            if (footBean.getResult().size()==0){
                foot_flag=true;
            }else{
                foot_flag=false;
            }
        }
    }

    @Override
    public void failure(String error) {

    }
}
