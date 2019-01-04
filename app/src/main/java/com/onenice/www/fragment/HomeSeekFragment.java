package com.onenice.www.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.onenice.www.R;
import com.onenice.www.adapter.ShowClassifyAdapter;
import com.onenice.www.adapter.ShowSearchAdapter;
import com.onenice.www.base.BaseFragment;
import com.onenice.www.bean.ShowClassifyBean;
import com.onenice.www.bean.ShowSearchBean;
import com.onenice.www.bean.ShowShopMsgBean;
import com.onenice.www.presenter.IpresenterImpl;
import com.onenice.www.util.Apis;
import com.onenice.www.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeSeekFragment extends BaseFragment implements IView {

    private XRecyclerView seek_xrecy;
    private ImageView seek_navigation;
    private ImageView seek_search;
    private RelativeLayout seek_rela_classify;
    private ImageView seek_classify_image;
    private TextView seek_classify_text;
    private int page;
    private IpresenterImpl mIpresenterImpl;
    private final int URL_COUNT=5;
    private final int COUNT_ITEM=2;
    private String shop_id;
    private boolean show_shop_flag;
    private ShowClassifyAdapter showClassifyAdapter;
    private EditText seek_search_edit;
    private ImageView seek_search_image;
    private String text;
    private XRecyclerView seek_search_xrecy;
    private ShowSearchAdapter showSearchAdapter;

    @Override
    protected int getlayoutResId() {
        return R.layout.home_seek_fragment;
    }

    @Override
    protected void initView(View view) {
        //获取资源ID
        seek_xrecy = view.findViewById(R.id.seek_xrecy);
        seek_navigation = view.findViewById(R.id.seek_navigation);
        seek_search = view.findViewById(R.id.seek_search);
        seek_rela_classify = view.findViewById(R.id.seek_rela_classify);
        seek_classify_image = view.findViewById(R.id.seek_classify_image);
        seek_classify_text = view.findViewById(R.id.seek_classify_text);
        seek_search_edit = view.findViewById(R.id.seek_search_edit);
        seek_search_image = view.findViewById(R.id.seek_search_image);
        seek_search_xrecy = view.findViewById(R.id.seek_search_xrecy);
        //注册
        EventBus.getDefault().register(this);
        initPresenter();
        //点击空白区域系统软键盘消失
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                getActivity().onTouchEvent(motionEvent);
                getVisibility(true);
                return false;
            }
        });


    }


    @Override
    protected void initData() {
        //初始化分类xrecy
        initXrecy();
        //初始化搜索商品xrecy
        initSearchXrecy();
        //点击按钮显示搜索框和搜索按钮
        initSearch();
        //点击搜索框进行搜索
        initSearchShop();
    }

    private void initSearchXrecy() {
        //布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),COUNT_ITEM);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        seek_search_xrecy.setLayoutManager(gridLayoutManager);
        //支持刷新和加载
        seek_search_xrecy.setPullRefreshEnabled(true);
        seek_search_xrecy.setLoadingMoreEnabled(true);
        seek_search_xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                initReachUrl(text,page);
            }

            @Override
            public void onLoadMore() {
                if (show_shop_flag){
                    seek_search_xrecy.loadMoreComplete();

                }else{
                    seek_search_xrecy.setLoadingMoreEnabled(true);
                }
                //加载
                initReachUrl(text,page);
            }
        });
        showSearchAdapter = new ShowSearchAdapter(getActivity());
        seek_search_xrecy.setAdapter(showSearchAdapter);
    }

    private void initSearchShop() {
        seek_search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seek_xrecy.setVisibility(View.VISIBLE);
                page=1;
                String shop_text = seek_search_edit.getText().toString();
                initReachUrl(shop_text,page);

                seek_search_edit.setVisibility(View.INVISIBLE);
                seek_search_image.setVisibility(View.INVISIBLE);
                seek_search.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initReachUrl(String shop_text, int page) {
        text=shop_text;
        mIpresenterImpl.getRequestIpresenter(String.format(Apis.SHOW_SEARCH_URL,shop_text,page,URL_COUNT),ShowClassifyBean.class);
    }

    private void initSearch() {
        seek_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVisibility(false);

                //自动加载软键盘
                setEditTextState();
            }
        });
    }

    private void initXrecy() {
        //布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),COUNT_ITEM);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        seek_xrecy.setLayoutManager(gridLayoutManager);
        //支持刷新和加载
        seek_xrecy.setPullRefreshEnabled(true);
        seek_xrecy.setLoadingMoreEnabled(true);
        seek_xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                initClassifyUrl(shop_id,page);

            }

            @Override
            public void onLoadMore() {
                if (show_shop_flag){
                    seek_xrecy.loadMoreComplete();

                }else{
                    seek_xrecy.setLoadingMoreEnabled(true);
                }
                //加载
                initClassifyUrl(shop_id,page);

            }
        });
        showClassifyAdapter = new ShowClassifyAdapter(getActivity());
        seek_xrecy.setAdapter(showClassifyAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void get(ShowShopMsgBean msgBean){

        switch (msgBean.getFlag()){
            //热销新品
            case "new":
                getVisibility(true);
                page=1;
                seek_classify_image.setImageResource(R.mipmap.bg_rxxp_syf);
                seek_classify_text.setText("热销新品");
                String newid = (String) msgBean.getObject();
                initClassifyUrl(newid,page);
                break;
            //魔力时尚
            case "fashion":
                getVisibility(true);
                page=1;
                seek_classify_image.setImageResource(R.mipmap.bg_mlss_syf);
                seek_classify_text.setText("魔力时尚");
                String fashionid = (String) msgBean.getObject();
                initClassifyUrl(fashionid,page);
                break;
            //品质生活
            case "live":
                getVisibility(true);
                page=1;
                seek_classify_image.setImageResource(R.mipmap.bg_pzsh_syf);
                seek_classify_text.setText("品质生活");
                String liveid = (String) msgBean.getObject();
                initClassifyUrl(liveid,page);
                Toast.makeText(getActivity(), liveid, Toast.LENGTH_SHORT).show();
                break;
            case "search":
                //搜索框和搜索按钮显示
                getVisibility(false);
                //自动加载软键盘
                setEditTextState();
                break;
            default:break;
        }

    }
    public void getVisibility(Boolean flag){
        if (flag){

            seek_search_edit.setVisibility(View.INVISIBLE);
            seek_search_image.setVisibility(View.INVISIBLE);
            seek_search.setVisibility(View.VISIBLE);
            seek_classify_image.setVisibility(View.VISIBLE);
            seek_classify_text.setVisibility(View.VISIBLE);
            seek_xrecy.setVisibility(View.VISIBLE);
            seek_xrecy.setVisibility(View.VISIBLE);
            seek_search_xrecy.setVisibility(View.GONE);
        }else{
            seek_search_edit.setVisibility(View.VISIBLE);
            seek_search_image.setVisibility(View.VISIBLE);
            seek_search.setVisibility(View.GONE);
            seek_classify_image.setVisibility(View.GONE);
            seek_classify_text.setVisibility(View.GONE);
            seek_xrecy.setVisibility(View.GONE);
            seek_search_xrecy.setVisibility(View.VISIBLE);
        }
    }
    private void initClassifyUrl(String id, int page) {
        shop_id=id;
        mIpresenterImpl.getRequestIpresenter(String.format(Apis.SHOW_CLASSIFY_URL,id,page,URL_COUNT),ShowSearchBean.class);
    }

    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //反注册
        EventBus.getDefault().unregister(this);
        //解绑
        mIpresenterImpl.Deatch();
        seek_search_edit.setText("");



    }

    @Override
    public void success(Object object) {
        if (object instanceof ShowClassifyBean){
            ShowClassifyBean showClassifyBean= (ShowClassifyBean) object;


            //停止刷新加载
            seek_xrecy.refreshComplete();
            seek_xrecy.loadMoreComplete();
            if (showClassifyBean.getResult().size()==0){
                show_shop_flag=true;
            }else{
                show_shop_flag=false;
            }
            page++;
            if (page==1){
                showClassifyAdapter.setList(showClassifyBean.getResult());
            }else{
                showClassifyAdapter.addList(showClassifyBean.getResult());
            }
        }
        if (object instanceof ShowSearchBean){
            ShowSearchBean showSearchBean= (ShowSearchBean) object;


            //停止刷新加载
            seek_xrecy.refreshComplete();
            seek_xrecy.loadMoreComplete();
            if (page==1){
                showSearchAdapter.setList(showSearchBean.getResult());
            }else{
                showSearchAdapter.addList(showSearchBean.getResult());
            }
            if (showSearchBean.getResult().size()==0){
                show_shop_flag=true;
            }else{
                show_shop_flag=false;
            }
            page++;

        }
    }

    @Override
    public void failure(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    private void setEditTextState() {
        //自动加载软键盘
        seek_search_xrecy.setVisibility(View.GONE);

        seek_search_edit.setFocusableInTouchMode(true);
        seek_search_edit.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager)seek_search_edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(seek_search_edit, 0);


    }


}
