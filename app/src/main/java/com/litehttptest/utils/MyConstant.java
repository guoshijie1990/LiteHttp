/*
 * (#)MyContants.java 1.0 2013-5-20 2013-5-20 GMT+08:00
 */
package com.litehttptest.utils;

import android.os.Environment;

/**
 * @author guoshijie
 * @version $1.0, 2013-5-20 2013-5-20 GMT+08:00
 * @since jdk1.7.0_17
 */
public class MyConstant {
    public final static int SEND_SUCCEED = 55; // 发送语音成功
    public final static int SEND_FAILED = 56; // 发送失败
    public final static int SEND_FAILED_unknow = 561; // 发送失败
    public final static int RECORDER_TIME = 57;// 录音计时
    public final static int STOP_RECOREDR = 58;// 停止录音
    public final static int PARSE_HTML = 59;// 解析网页
    public final static int SHOW_RECORDER_DIALOG = 510;// 解析网页
    public final static int SHOW_CHOOSEPHOTO_DIALOG = 513;// 解析网页
    public final static int LOGIN_FAILED = 511;// 登陆失败,用户名密码错误
    public final static int LOGIN_PARSE_FAILED = 512;// 登陆失败,用户名密码错误

    public final static int TAB_HOME = 0;
    public final static int TAB_CARDCASE = 1;
    public final static int TAB_SMALLCARD = 2;
    public final static int TAB_CREATE = 3;
    public final static int TAB_SETTING = 4;

    /** Handler What加载数据完毕 **/
    public static final int WHAT_DID_LOAD_DATA = 0;
    /** Handler What第一次运行加载数据失败 **/
    public static final int WHAT_DID_LOAD_DATA_FAILED = 1;
    /** Handler What第一次运行加载数据没有网络 **/
    public static final int WHAT_DID_LOAD_DATA_NO_NETWORK = 111;

    /** Handler What个人信息加载数据完毕 **/
    public static final int WHAT_DID_LOAD_DATA_WEIBO = 100;
    /** Handler What个人信息第一次运行加载数据失败 **/
    public static final int WHAT_DID_LOAD_DATA_FAILED_WEIBO = 101;
    /** Handler What个人信息第一次运行加载数据没有网络 **/
    public static final int WHAT_DID_LOAD_DATA_NO_NETWORK_WEIBO = 10111;

    /** Handler What刷新数据完毕 **/
    public static final int WHAT_DID_REFRESH = 2;
    /** Handler What刷新数据失败 **/
    public static final int WHAT_DID_REFRESH_FAILED = 3;
    /** Handler What刷新数据没有网络 **/
    public static final int WHAT_DID_REFRESH_NO_NETWORK = 4;

    /** Handler What更多数据完毕 **/
    public static final int WHAT_DID_MORE = 5;
    /** Handler What更多数据失败 **/
    public static final int WHAT_DID_MORE_FAILED = 6;
    /** Handler What更多数据没有网络 **/
    public static final int WHAT_DID_MORE_NO_NETWORK = 7;

    /** Handler What加载关键字完毕 **/
    public static final int WHAT_DID_LOAD_DATA_KEY = 8;

    /** Handler What定位 **/
    public static final int WHAT_LOCATION = 9;
    /** Handler What定位失败 **/
    public static final int WHAT_LOCATION_FAIL = 10;

    /** Handler What更新上传进度条 **/
    public static final int WHAT_UPLOAD_DOING = 11;

    /** Handler What登陆画面加载完毕 **/
    public static final int WHAT_LOGIN_PIC = 12;

    /** Handler What没有网络连接 **/
    public static final int WHAT_NO_NETWORK = 13;

    /** Handler What搜索未找到 **/
    public static final int WHAT_SEARCH_NO = 14;

    /** Handler What登陆成功 **/
    public static final int WHAT_LOGIN_SUCCESS = 15;
    /** Handler What登陆失败 **/
    public static final int WHAT_LOGIN_FAILED = 16;
    /** Handler What登陆黑名单 **/
    public static final int WHAT_LOGIN_FAILED_BLACKLIST = 161;

    /** Handler What超时 **/
    public static final int WHAT_TIMEOUT = 17;
    /** Handler What取消上传 **/
    public static final int WHAT_CANCLE_UPLOAD = 171;

    /** Handler What攒 成功 **/
    public static final int WHAT_AGREE_SUCCESS = 18;
    /** Handler What取消攒 成功 **/
    public static final int WHAT_AGREE_FAILED = 19;

    /** Handler What关注 成功 **/
    public static final int WHAT_GUANZHU_FAILED = 20;
    /** Handler What关注 成功 **/
    public static final int WHAT_GUANZHU_SUCCESS = 21;
    /** Handler What取消关注 成功 **/
    public static final int WHAT_GUANZHU_CANCLE_FAILED = 22;
    /** Handler What取消关注 成功 **/
    public static final int WHAT_GUANZHU_CANCLE_SUCCESS = 23;

    /** Handler What修改 成功 **/
    public static final int WHAT_CHANGE_SUCCESS = 24;
    /** Handler What修改 失败 **/
    public static final int WHAT_CHANGE_FAILED = 25;

    /** Handler What收藏 失败 **/
    public static final int WHAT_COLLECT_FAILED = 26;
    /** Handler What收藏 成功 **/
    public static final int WHAT_COLLECT_SUCCESS = 27;

    /** Handler What取消收藏 成功 **/
    public static final int WHAT_UNCOLLECT_FAILED = 28;
    /** Handler What取消收藏 成功 **/
    public static final int WHAT_UNCOLLECT_SUCCESS = 29;

    /** Handler What没有评论 **/
    public static final int WHAT_NO_LIST = 30;
    public static final int WHAT_NOT_EXIST = 31;

    /** Handler What删除评论失败 **/
    public static final int WHAT_DELETE_COMMENT_FAILED = 32;
    /** Handler What删除 成功 **/
    public static final int WHAT_DELETE_COMMENT_SUCCESS = 33;
    /** Handler What删除微博失败 **/
    public static final int WHAT_DELETE_WEIBO_FAILED = 34;
    /** Handler What删除微博成功 **/
    public static final int WHAT_DELETE_WEIBO_SUCCESS = 35;

    // public static final String
    // SCARD_PATH=Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SCARD_PATH = Environment
	    .getExternalStorageDirectory().getPath();
    // public static final String ERROR_PATH=SCARD_PATH+"/.weimingpian/error/";
    // public static final String ERROR_FILE="cclog.txt";
    public static final String CACHE_VIDEO_CACHE_PATH = SCARD_PATH
	    + "/.weimingpian/cache/downloadvideo/";// 下载的视频路径
    public static final String CACHE_VIDEO_CACHE_MYSELF_PATH = SCARD_PATH
	    + "/.weimingpian/me/downloadvideo/";// 自己的视频下载的视频路径

    // public static final String
    // CACHE_PHOTO_TEMP_PATH=SCARD_PATH+"/.weimingpian/cache/image/temp/";//拍照头像路径
    // public static final String
    // CACHE_PHOTO_PATH=SCARD_PATH+"/.weimingpian/cache/image/photo/";//头像路径
    // public static final String CACHE_PHOTO_IMAGE_Name="photo.jpg";//头像名称

    public static final String CACHE_IMAGE_PATH = "/.weimingpian/cache/image";// 图片缓存路径

    public static final String MYSELF_NAME = "myself.json";// 个人信息
    public static final String PASSWORD_NAME="password";
    public static final String ADDRESS="address.json";
    public static final String LOCATOIN_SEND_NAME = "driver.json";// 配送司机

    public static final int page_size = 15;
//    public static final String url = "https://122.114.19.181";
    public static final String url = "https://app.bolaihui.com/app";
    // 下载地址
    public static String downUrl = MyConstant.url + "apk/download.htm?code=";

    public static final String APP_ID = "wxa895b263d5253845";// 微信

    public static final boolean isdebug=true;
}
