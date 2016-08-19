package com.litehttptest;

import android.text.TextUtils;

import com.litehttptest.utils.DeviceInfo;
import com.litehttptest.utils.LogUtil;
import com.litehttptest.utils.MyConstant;
import com.litesuits.http.LiteHttp;
import com.litesuits.http.data.GsonImpl;
import com.litesuits.http.impl.huc.HttpUrlClient;

import java.util.HashMap;

/**
 * Created by shijie.guo on 2016-7-27.
 */
public class RequestControl {
    public static final String TAG = "RequestControl";
    private volatile static RequestControl mInstance = null;
    protected static LiteHttp liteHttp;
    private HashMap<String, String> headers;

    public static RequestControl getInstance() {
        if (mInstance == null) {
            synchronized (RequestControl.class) {
                if (mInstance == null) {
                    mInstance = new RequestControl();
                }
            }
        }
        return mInstance;
    }

    public RequestControl() {
        headers = new HashMap<String, String>();
        headers.put("client-platform", "1");
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("imei", DeviceInfo.getInstance(MyApplication.getApplication()).getDiu());
        LogUtil.d("imei=" + DeviceInfo.getInstance(MyApplication.getApplication()).getDiu() + "");
//        if (UserControl.getInstance().getUserInfo() != null) {
//            LogUtil.d("authorization-token="+UserControl.getInstance().getUserInfo().getToken() + "");
//            headers.put("authorization-token", UserControl.getInstance().getUserInfo().getToken() + "");
//        }
        initLiteHttp();
    }

    public static LiteHttp getLiteHttp() {
        return liteHttp;
    }

    /**
     * 单例 keep an singleton instance of litehttp
     */
    private void initLiteHttp() {
        if (liteHttp == null) {
            liteHttp = LiteHttp.build(MyApplication.getApplication())
                    .setHttpClient(new HttpUrlClient())       // http client
                    .setJsonConvertor(new GsonImpl())        // json convertor
                    .setBaseUrl(MyConstant.url)                    // set base url
                    .setDebugged(true)                     // log output when debugged
                    .setDoStatistics(true)                // statistics of time and traffic
                    .setDetectNetwork(true)
//                    .setDefaultCacheMode(CacheMode.CacheFirst)
                    // detect network before connect
//                    .setUserAgent("Mozilla/5.0 (...)")  // set custom User-Agent
                    .setSocketTimeout(10000)           // socket timeout: 10s
                    .setConnectTimeout(10000)         // connect timeout: 10s
                    .setCommonHeaders(headers)
                    .create();
        } else {

            liteHttp.getConfig()                   // configuration directly
                    .setSocketTimeout(5000)       // socket timeout: 5s
                    .setConnectTimeout(5000);    // connect timeout: 5s
        }
    }

    public void cancelPendingRequests(String tag) {
        if (liteHttp != null) {
            liteHttp.cancelAll(TextUtils.isEmpty(tag) ? TAG : tag);
        }
    }
}
