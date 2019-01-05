package com.onenice.www.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.onenice.www.R;
import com.onenice.www.activity.MainActivity;
import com.onenice.www.adapter.CircleListAdapter;
import com.onenice.www.base.BaseFragment;
import com.onenice.www.bean.CircleLikeBean;
import com.onenice.www.bean.CircleListBean;
import com.onenice.www.bean.LoginBean;
import com.onenice.www.bean.ShowClassifyBean;
import com.onenice.www.presenter.IpresenterImpl;
import com.onenice.www.util.Apis;
import com.onenice.www.view.IView;

import java.util.HashMap;
import java.util.Map;

public class CircleFragment extends BaseFragment implements IView {

    private XRecyclerView circle_xrecy;
    private int circle_page;
    private boolean circle_flag;
    private IpresenterImpl mIpresenterImpl;
    private final int COUNT_ITEM = 5;
    private CircleListAdapter circleListAdapter;

    @Override
    protected int getlayoutResId() {
        return R.layout.circle_fragment;
    }

    @Override
    protected void initView(View view) {
        //获取资源ID
        circle_xrecy = view.findViewById(R.id.circle_xrecy);
    }

    @Override
    protected void initData() {
        initPresenter();
        initXrecy();
    }

    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    private void initXrecy() {
        circle_page=1;
        //布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        circle_xrecy.setLayoutManager(linearLayoutManager);

        //支持刷新和加载
        circle_xrecy.setPullRefreshEnabled(true);
        circle_xrecy.setLoadingMoreEnabled(true);
        circleListAdapter = new CircleListAdapter(getActivity());
        circle_xrecy.setAdapter(circleListAdapter);
        circle_xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                circle_page=1;
                initCircleUrl(circle_page);
            }
            @Override
            public void onLoadMore() {
                if (circle_flag){
                    circle_xrecy.loadMoreComplete();

                }else{
                    circle_xrecy.setLoadingMoreEnabled(true);
                }
                //加载
                initCircleUrl(circle_page);
            }
        });
        initCircleUrl(circle_page);

        circleListAdapter.setOnClick(new CircleListAdapter.OnClick() {
            @Override
            public void getdata(int id, int great, int position) {
                if (great==1){
                    //flag为true,已点赞，需取消
                    cancelCircleUrl(id);
                    circleListAdapter.getcancel(position);
                }else{
                    //flag为false，未点赞，需点赞
                    needlCircleUrl(id);
                    circleListAdapter.getlike(position);
                }
            }
        });
    }

    private void needlCircleUrl(int id) {
        Map<String,String> params=new HashMap<>();
        params.put("circleId",String.valueOf(id));
        mIpresenterImpl.postRequestIpresenter(Apis.SHOW_CIRCLE_LIKE_URL,params,CircleLikeBean.class);
    }

    private void cancelCircleUrl(int id) {
        mIpresenterImpl.deleteRequestIpresenter(String.format(Apis.SHOW_CIRCLE_CANCEL_URL,id),CircleLikeBean.class);
    }

    private void initCircleUrl(int circle_page) {
       mIpresenterImpl.getRequestIpresenter(String.format(Apis.SHOW_CIRCLE_LIST_URL,circle_page,COUNT_ITEM),CircleListBean.class);

    }

    @Override
    public void success(Object object) {
        if (object instanceof CircleListBean){
            CircleListBean circleListBean= (CircleListBean) object;
            if (circle_page==1){
                circleListAdapter.setList(circleListBean.getResult());
            }else{
                circleListAdapter.addList(circleListBean.getResult());
            }
            //停止刷新加载
            circle_xrecy.refreshComplete();
            circle_xrecy.loadMoreComplete();
            circle_page++;
            if (circleListBean.getResult().size()==0){
                circle_flag=true;
            }else{
                circle_flag=false;
            }
        }
        if (object instanceof CircleLikeBean){
            CircleLikeBean circleLikeBean= (CircleLikeBean) object;
            Toast.makeText(getActivity(), circleLikeBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failure(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
