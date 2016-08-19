package com.litehttptest.utils;

import android.util.Log;

import com.litehttptest.BuildConfig;


/**
 * 日志工具
 *
 * @author shijie.guo
 */
public abstract class LogUtil {

    public static void d(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d("bolaihui", msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e("bolaihui", msg);
        }
    }
}
