package com.onenice.www.presenter;

import com.onenice.www.model.ModelCallBack;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @详情 P层
 *
 * @创建日期 2018/12/29 15:01
 *
 */
public interface Ipresenter {

    //get请求
    void getRequestIpresenter(String url, Class clazz);
    //post请求
    void postRequestIpresenter(String url, Map<String,String> params,Class clazz);
    //get请求
    void deleteRequestIpresenter(String url, Class clazz);
    //put请求
    void putRequestIpresenter(String url, Map<String,String> params,Class clazz);
    //上传图片
    void postImageRequestIpresenter(String url, List<File> image_list,Class clazz);
    void postimageRequestIpresenter(String url,File file,Class clazz);
    //图文上传
    void postImageConRequestIpresenter(String url, Map<String,String> params ,File file,Class clazz);
    //多张图片
    void postDuoConRequestIpresenter(String url,Map<String,String> params, List<File> list , Class clazz);

}
