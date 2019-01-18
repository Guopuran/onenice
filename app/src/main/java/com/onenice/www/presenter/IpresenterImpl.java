package com.onenice.www.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.onenice.www.MyApplication;
import com.onenice.www.model.ImodelImpl;
import com.onenice.www.model.ModelCallBack;
import com.onenice.www.view.IView;

import java.io.File;
import java.util.List;
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
    public void  deatch(){
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
    //delete请求
    @Override
    public void deleteRequestIpresenter(String url, Class clazz) {
        mImodelImpl.deleteRequestModel(url , clazz , new ModelCallBack() {

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
    public void putRequestIpresenter(String url, Map<String, String> params, Class clazz) {
        mImodelImpl.putRequestModel(url , params , clazz, new ModelCallBack() {

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

    @Override
    public void postImageRequestIpresenter(String url, List<File> image_list, Class clazz) {
        mImodelImpl.postImageRequestModel(url, image_list, clazz, new ModelCallBack() {
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

    @Override
    public void postimageRequestIpresenter(String url, File file, Class clazz) {
        mImodelImpl.postimageRequestModel(url, file, clazz, new ModelCallBack() {
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

    @Override
    public void postImageConRequestIpresenter(String url, Map<String, String> params, File file, Class clazz) {
        mImodelImpl.postImageConRequestModel(url,params ,file, clazz, new ModelCallBack() {
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

    @Override
    public void postDuoConRequestIpresenter(String url, Map<String, String> params, List<File> list, Class clazz) {
        mImodelImpl.postDuoConRequestModel(url,params ,list, clazz, new ModelCallBack() {
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
