package com.litehttptest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class DeviceInfo {

    private final String mModel = Build.MODEL;
    private final String mDevice = Build.DEVICE;
    private final String mManufacture = Build.MANUFACTURER;
    private int mSDKVersion = Build.VERSION.SDK_INT;
    private String mAMapVersion = "";
    private String mGLRender = "";
    private static String mNetType = null;
    private int mnc;
    private int mcc;
    private int lac;
    private int cid;
    private int bid;
    private int sid;
    private int nid;
    private int nt;
    private int pt;
    private int ant;
    private int mLon;
    private int mLat;
    private int mAccurate;
    private int mWidth; // 屏幕宽度
    private int mHeight; // 屏幕高度
    private float mDensity; // 屏幕密度
    private final int mDensityDpi; // 密度dpi值
    private int mStrength;
    private int mStartTime;
    private int mStopTime;
    private int mLastStartTime = -1;
    private int mLastStopTime = -1;
    private final Context mContext;

    private static DeviceInfo instance;

    /**
     * 采用懒汉式单利模式，双重加锁机制
     *
     * @param context
     * @return
     */
    public synchronized static DeviceInfo getInstance(Context context) {
        if (instance == null) {
            synchronized (DeviceInfo.class) {
                instance = new DeviceInfo(context);
            }
            return instance;
        }
        // 当横竖屏切换时，重新获取屏幕参数
        else {
            if (context.getResources().getDisplayMetrics().widthPixels != instance
                    .getScreenWidth()) {
                synchronized (DeviceInfo.class) {
                    instance = new DeviceInfo(context);
                }
            }
        }
        return instance;
    }

    private DeviceInfo(Context context) {
        mContext = context.getApplicationContext();
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        mDensity = metrics.density;
        mDensityDpi = metrics.densityDpi;
        PackageManager pm = mContext.getPackageManager();
        String str = mContext.getPackageName();
        try {
            mAMapVersion = pm.getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {

        }
    }

    public String getAMapVersion() {
        return mAMapVersion;
    }

    public void setGLRender(String glRender) {
        mGLRender = glRender;
    }

    public void setLocation(int lon, int lat, int ac) {
        mLon = lon;
        mLat = lat;
        mAccurate = ac;
        if (mAccurate > Short.MAX_VALUE)
            mAccurate = Short.MAX_VALUE;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        if (mWidth == 0) {
            DisplayMetrics metrics = mContext.getResources()
                    .getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
            mDensity = metrics.density;
        }
        return mWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        if (mHeight == 0) {
            DisplayMetrics metrics = mContext.getResources()
                    .getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
            mDensity = metrics.density;
        }
        return mHeight;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public float getScreenDensity() {
        if (mDensity == 0) {
            DisplayMetrics metrics = mContext.getResources()
                    .getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
            mDensity = metrics.density;
        }
        return mDensity;
    }

    /**
     * 获取屏幕密度dpi值
     *
     * @return
     */
    public int getScreenDensityDpi() {
        if (mDensityDpi == 0) {
            DisplayMetrics metrics = mContext.getResources()
                    .getDisplayMetrics();
            mWidth = metrics.widthPixels;
            mHeight = metrics.heightPixels;
            mDensity = metrics.density;
        }
        return mDensityDpi;
    }

    public void setStrength(int strength) {
        mStrength = strength;
    }

    private static String diu;

    public  String getDiu() {

        if (diu != null) {
            return diu;
        }

        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            diu = mTelephonyManager.getDeviceId();
        } catch (Exception e) {
        }

        if (diu == null || diu.length() <= 0) {
            diu = Settings.Secure.getString(mContext.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }

        if (diu == null || diu.length() <= 0) {
            SharedPreferences mSharedPreferences = mContext
                    .getSharedPreferences("SharedPreferences", 0);
            diu = mSharedPreferences.getString("diu", genUid());
        }

        if (diu == null || diu.length() <= 0) {

            diu = genUid();
        }

        return diu;
    }

    private  String genUid() {
        java.util.Random rnd = new java.util.Random();
        int rint = (rnd.nextInt() >>> 1) % 8999;
        rint += 1000;
        String uid = "AND" + rint + "" + System.currentTimeMillis();

        SharedPreferences.Editor editor = mContext.getSharedPreferences("SharedPreferences", 0).edit();
        editor.putString("diu", uid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }

        return uid;
    }
}
