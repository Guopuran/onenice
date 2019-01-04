package com.onenice.www.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.onenice.www.MyApplication;
import com.onenice.www.model.ImodelImpl;
import com.onenice.www.model.ModelCallBack;
import com.onenice.www.view.IView;

import java.util.Map;

/**
 *
 * @详情 M层实现类
 *
 * @创建日期 2018/12/29 15:01
 *
 */
public class IpresenterImpl implements Ipresenter{

    private ImodelImpl mImodelImpl;
    private IView mIView;

    public IpresenterImpl(IView mIView) {
        this.mIView = mIView;
        //实例化
        mImodelImpl=new ImodelImpl();
    }

    //解绑
    public void  Deatch(){
        //解绑M层
        if (mImodelImpl!=null){
            mImodelImpl=null;
        }

        //解绑V层
        if (mIView!=null){
            mIView=null;
        }
    }

    //get请求
    @Override
    public void getRequestIpresenter(String url, Class clazz) {
        mImodelImpl.getRequestModel(url , clazz , new ModelCallBack() {

            @Override
            public void success(Object object) {
                mIView.success(object);
            }

            @Override
            public void failure(String error) {
                mIView.failure(error);
            }
        });
    }

    //post请求
    @Override
    public void postRequestIpresenter(String url, Map<String, String> params, Class clazz) {
        mImodelImpl.postRequestModel(url , params , clazz, new ModelCallBack() {

            @Override
            public void success(Object object) {
                mIView.success(object);
            }

            @Override
            public void failure(String error) {
                mIView.failure(error);
            }
        });
    }

}
