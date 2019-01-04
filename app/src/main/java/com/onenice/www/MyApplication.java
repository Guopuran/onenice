package com.onenice.www;

import android.app.Application;
import android.graphics.Point;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    public static MyApplication instance;
    //绘制页面时参照的设计图宽度
    public final static float DESIGN_WIDTH = 750;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        //沉浸式状态栏
        immersive();
        //初始化Fresco
        initFresco();
        Fresco.initialize(this);
    }

    private void initFresco() {

    }

    private void immersive() {
        Point mPoint=new Point();
        ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(mPoint);
        getResources().getDisplayMetrics().xdpi=mPoint.x/DESIGN_WIDTH*72f;
    }

    public static MyApplication getContext(){
        return instance;
    }
}
