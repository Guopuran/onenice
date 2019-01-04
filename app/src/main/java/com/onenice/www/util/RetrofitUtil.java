package com.onenice.www.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @详情 Retrofit网络请求的工具类
 *
 * @创建日期 2018/12/29 15:02
 *
 */
public class RetrofitUtil {

    private static RetrofitUtil instance;
    private ObservedApis mObservedApis;
    private final String BaseUrl="http://mobile.bwstudent.com/small/";

    public static synchronized RetrofitUtil getInstance(){
        if (instance==null){
            instance=new RetrofitUtil();
        }
        return instance;
    }

    public RetrofitUtil(){
        //拦截器
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mClient=new OkHttpClient.Builder()
                //读取超时
                .readTimeout(10,TimeUnit.SECONDS)
                //连接超时
                .connectTimeout(10,TimeUnit.SECONDS)
                //写超时
                .writeTimeout(10,TimeUnit.SECONDS)
                //添加拦截器
                .addInterceptor(interceptor)
                .build();

        //Retrofit的创建
        Retrofit mRetrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .client(mClient)
                .build();
        mObservedApis=mRetrofit.create(ObservedApis.class);
    }

    //get请求
    public void get(String url,ICallBack callBack){
        mObservedApis.get(url)
                //后执行在哪个线程
                .subscribeOn(Schedulers.io())
                //最终完成后执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));

    }


    //post请求
    public void post(String url, Map<String,String> params,ICallBack callBack){
        if (params==null){
            params=new HashMap<>();
        }
        mObservedApis.post(url,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
    }

    private Observer getObserver(final ICallBack callBack) {
        //Rxjava
         Observer observer=new Observer<ResponseBody>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (callBack!=null){
                    callBack.failure(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    if (callBack!=null){
                        callBack.success(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (callBack!=null){
                        callBack.failure(e.getMessage());
                    }
                }
            }
        };
    return observer;
    }



    public interface ICallBack{
        void success(String result);
        void failure(String error);
    }
}
