package com.onenice.www.util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 * @详情 RXjava的被观察者
 *
 * @创建日期 2018/12/29 15:00
 *
 */
public interface ObservedApis {

    @GET
    Observable<ResponseBody> get(@Url String url);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> parmas);
    @DELETE
    Observable<ResponseBody> delete(@Url String url);
}
