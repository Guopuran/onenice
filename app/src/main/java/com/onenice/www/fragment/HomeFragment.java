package com.onenice.www.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.onenice.www.R;
import com.onenice.www.base.BaseFragment;
import com.onenice.www.bean.ShowShopMsgBean;
import com.onenice.www.customview.CustomViewpagerHome;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @详情 展示的fragment
 *
 * @创建日期 2018/12/31 17:57
 *
 */
public class HomeFragment extends BaseFragment {
    private CustomViewpagerHome home_viewpager;
    private List<Fragment> list;
    @Override
    protected int getlayoutResId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {
        //获取资源ID
        home_viewpager = view.findViewById(R.id.home_viewpager);
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        //添加fragment
        initfrag();
        home_viewpager.setCurrentItem(1);

}

    private void initfrag() {
        list=new ArrayList<>();
        list.add(new HomeParticularsFragment());
        list.add(new HomeShowFragment());
        list.add(new HomeSeekFragment());
        home_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        home_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (home_viewpager.getCurrentItem()==1){
                    //禁止滑动
                    home_viewpager.setScroll(false);

                    hideIputKeyboard(getActivity());


                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getIndex(ShowShopMsgBean msgBean){
        if (!(msgBean.getFlag().equals(""))){
            //允许滑动
            home_viewpager.setScroll(true);
            home_viewpager.setCurrentItem(2);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    public  void hideIputKeyboard(final Context context) {
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mInputKeyBoard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (activity.getCurrentFocus() != null) {
                    mInputKeyBoard.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),        InputMethodManager.HIDE_NOT_ALWAYS);
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
            }
        });
    }
}
