package com.tsp.newsdemo;

import android.app.Application;

import org.xutils.x;

/**
 * 所有组件创建之前执行
 */
public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //开启调试 会影响性能
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
