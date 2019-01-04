package com.onenice.www.presenter;

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
}
