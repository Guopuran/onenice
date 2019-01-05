package com.onenice.www.model;

import java.util.Map;

/**
 *
 * @详情 M层
 *
 * @创建日期 2018/12/29 15:00
 *
 */
public interface Imodel {

    //get请求
    void getRequestModel(String url,Class clazz,ModelCallBack callBack);

    //post请求
    void postRequestModel(String url, Map<String,String> params,Class clazz,ModelCallBack callBack);
    //delete请求
    void deleteRequestModel(String url,Class clazz,ModelCallBack callBack);
}
