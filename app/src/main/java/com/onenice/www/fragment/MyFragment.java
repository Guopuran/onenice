package com.onenice.www.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.onenice.www.R;
import com.onenice.www.activity.AddressActivity;
import com.onenice.www.activity.DataActivity;
import com.onenice.www.activity.FootActivity;
import com.onenice.www.activity.MainActivity;
import com.onenice.www.activity.MoneyActivity;
import com.onenice.www.base.BaseFragment;
import com.onenice.www.bean.LoginBean;
import com.onenice.www.presenter.IpresenterImpl;
import com.onenice.www.view.IView;

import java.io.File;

import retrofit2.http.POST;

/**
 *
 * @描述 我的模板
 *
 * @创建日期 2019/1/9 10:56
 *
 */
public class MyFragment extends BaseFragment implements View.OnClickListener,IView {

    private TextView text_personal;
    private TextView text_circle;
    private TextView text_foot;
    private TextView text_wallet;
    private TextView text_site;
    private SimpleDraweeView my_image_header;
    private IpresenterImpl mIpresenterImpl;
    private LoginBean.ResultBean result;
    private PopupWindow popupWindow;

    private static final int PHOTO_REQUEST=1;//拍照
    private static final int PHOTO_CHECK=2;//从相册中选择
    private static final int PHOTO_REQUEST_CUT=3;//裁剪之后
    private static final String PHOTO_FILE_MAME="header_image.jpg";//临时文件名
    private File file;

    @Override
    protected int getlayoutResId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {
        //获取资源ID
        text_personal = view.findViewById(R.id.my_text_personal);
        text_circle = view.findViewById(R.id.my_text_circle);
        text_foot = view.findViewById(R.id.my_text_foot);
        text_wallet = view.findViewById(R.id.my_text_wallet);
        text_site = view.findViewById(R.id.my_text_address);
        my_image_header = view.findViewById(R.id.my_image_header);
        TextView text_name=view.findViewById(R.id.my_text_name);

        //互绑
        initPresenter();
        text_personal.setOnClickListener(this);
        text_circle.setOnClickListener(this);
        text_foot.setOnClickListener(this);
        text_wallet.setOnClickListener(this);
        text_site.setOnClickListener(this);
        my_image_header.setOnClickListener(this);
        my_image_header.setImageURI(Uri.parse("res://com.onenice.www/" + R.mipmap.bg_wdf));
        result = ((MainActivity) getActivity()).getResult();
        //设置登录名
        text_name.setText(result.getNickName());
        my_image_header.setImageURI(Uri.parse(result.getHeadPic()));

    }



    @Override
    protected void initData() {
        initPop();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            //个人资料
            case R.id.my_text_personal:
                Intent intent =new Intent(getActivity(),DataActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);
                break;
            //朋友圈
            case R.id.my_text_circle:

                break;
            //足迹
            case R.id.my_text_foot:
                Intent intent1=new Intent(getActivity(),FootActivity.class);
                startActivity(intent1);
                break;
            //钱包
            case R.id.my_text_wallet:
                Intent intent2=new Intent(getActivity(),MoneyActivity.class);
                startActivity(intent2);
                break;
            //地址
            case R.id.my_text_address:
                Intent intent3=new Intent(getActivity(),AddressActivity.class);
                startActivity(intent3);
                break;
            case R.id.my_image_header:
                popupWindow.showAsDropDown(v,-45,8);


                break;
        }
    }

    private void initPop() {
        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(getActivity()).inflate(R.layout.pop_image, null, false);

        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, true);
       /* // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);*/
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.colorpop));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        //点击弹出
    }

    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑
        mIpresenterImpl.deatch();
    }

    @Override
    public void success(Object object) {

    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    private long exitTime=0;
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

                    //双击退出
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        getActivity().finish();
                        System.exit(0);
                    }
                    return true;
                }

                return false;
            }
        });
    }

}
