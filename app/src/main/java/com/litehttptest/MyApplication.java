package com.litehttptest;

import android.app.Application;

/**
 * Created by shijie.guo on 2016-7-27.
 */
public class MyApplication extends Application {
    public static MyApplication sInstance;

    public static synchronized MyApplication getApplication() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        RequestControl.getInstance();
    }
}
