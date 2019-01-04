package com.onenice.www.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * @详情 Activity基类
 *
 * @创建日期 2018/12/29 15:01
 *
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView(savedInstanceState);
        initData();
    }

    protected abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();




}
