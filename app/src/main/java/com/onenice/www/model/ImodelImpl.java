package com.onenice.www.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.onenice.www.MyApplication;
import com.onenice.www.util.RetrofitUtil;

import java.util.Map;

/**
 *
 * @详情 M层实现类
 *
 * @创建日期 2018/12/29 15:01
 *
 */
public class ImodelImpl implements Imodel {

    //get请求
    @Override
    public void getRequestModel(String url, final Class clazz, final ModelCallBack callBack) {
        if (!isNetWork()){
            callBack.failure("网络状态不可用");
            return;
        }
        RetrofitUtil.getInstance().get(url, new RetrofitUtil.ICallBack() {
            @Override
            public void success(String result) {
                Object object = getGson(result, clazz);
                callBack.success(object);
            }

            @Override
            public void failure(String error) {
                callBack.failure(error);
            }
        });
    }

    //post请求
    @Override
    public void postRequestModel(String url, Map<String, String> params, final Class clazz, final ModelCallBack callBack) {
        if (!isNetWork()){
            callBack.failure("网络状态不可用");
            return;
        }
        RetrofitUtil.getInstance().post(url, params, new RetrofitUtil.ICallBack() {
            @Override
            public void success(String result) {
                Object object = getGson(result, clazz);
                callBack.success(object);
            }

            @Override
            public void failure(String error) {
                callBack.failure(error);
            }
        });
    }

    //gson解析
    private Object getGson(String result, Class clazz) {
        Object o = new Gson().fromJson(result, clazz);
        return o;
    }

    //判断网络状态
    public static boolean isNetWork(){
        ConnectivityManager cm = (ConnectivityManager) MyApplication.instance.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isAvailable();
    }
}
