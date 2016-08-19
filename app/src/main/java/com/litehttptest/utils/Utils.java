/*
 * (#)Utils.java 1.0 2013-5-2 2013-5-2 GMT+08:00
 */
package com.litehttptest.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.litehttptest.MyApplication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author guoshijie
 * @version $1.0, 2013-5-2 2013-5-2 GMT+08:00
 * @since jdk1.7.0_17
 */
public class Utils {



	public static boolean CheckNameisChinese(String name){
		return  checkNameChese(name) && compileExChar(name);
	}
	/**
	 * 判定输入汉字
	 * @param c
	 * @return
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**

	 * @prama: str 要判断是否包含特殊字符的目标字符串

	 */

	private static boolean compileExChar(String str){
		boolean res=true;
		String limitEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		Pattern pattern = Pattern.compile(limitEx);
		Matcher m = pattern.matcher(str);

		if( m.find()){
			res=false;
		}
		return res;
	}

	/**
	 * 检测String是否全是中文
	 * @param name
	 * @return
	 */
	private static  boolean checkNameChese(String name)
	{
		boolean res=true;
		char [] cTemp = name.toCharArray();
		for(int i=0;i<name.length();i++)
		{
			if(!isChinese(cTemp[i]))
			{
				res=false;
				break;
			}
		}

		return res;
	}



	public static String LongToTime(long time){
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(time);
		return now.get(Calendar.YEAR)+"年"+(now.get(Calendar.MONTH) + 1)+"月"+now.get(Calendar.DAY_OF_MONTH)+"日";
	}

	public static String getCurrentTime(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d1=new Date(System.currentTimeMillis());
		return format.format(d1);
	}
    /**
     * 保存用户更换的头像
     * 
     * @param context
     * @param key
     * @param value
     */
//    public static void updataUserLogo(Context context, String value) {
//	String json = ACache.get(context).getAsString(MyConstant.MYSELF_NAME);
//	if (!TextUtils.isEmpty(json)) {
//	    Gson gson = new Gson();
//	    UserResult result = gson.fromJson(json, UserResult.class);
//	    UserData userData = result.getData();
//	    userData.setLogoUrl(value);
//	    ACache.get(context).put(MyConstant.MYSELF_NAME,
//		    gson.toJson(userData));
//	}
//    }

    /**
     * 保存导游证
     * 
     * @param context
     * @param key
     * @param value
     */
//    public static void updataUserGuideCardUrl(Context context, String value) {
//	String json = ACache.get(context).getAsString(MyConstant.MYSELF_NAME);
//	if (!TextUtils.isEmpty(json)) {
//	    Gson gson = new Gson();
//	    UserResult result = gson.fromJson(json, UserResult.class);
//	    UserData userData = result.getData();
//	    userData.setGuideCardUrl(value);
//	    ACache.get(context).put(MyConstant.MYSELF_NAME,
//		    gson.toJson(userData));
//	}
//    }

    private Bitmap compressImage(Bitmap image) {

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
	int options = 100;
	while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
	    baos.reset();// 重置baos即清空baos
	    image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
	    options -= 10;// 每次都减少10
	}
	ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
	Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
	return bitmap;
    }

    public String LongToData(long time) {
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
	Date dt = new Date(time);
	String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
	return sDateTime;
    }

    // /**
    // * 添加照片
    // * @param context
    // * @param photoarr
    // * @return
    // */
    // public static ArrayList<Photoarr> SavePhoto(Context context,Photoarr
    // photoarr){
    // UserResult result=null;
    // String json=ACache.get(context).getAsString(MyConstant.MYSELF_NAME);
    // if(json!=null && !json.equals("")){
    // result=LoginParse.parse(json);
    // ArrayList<Photoarr> arrayList;
    // arrayList=result.getData().getPhotoarr();
    // if(arrayList==null){
    // arrayList=new ArrayList<Photoarr>();
    // arrayList.add(photoarr);
    // }else{
    // arrayList.add(photoarr);
    // }
    // result.getData().setPhotoarr(arrayList);
    // Gson gson=new Gson();
    // ACache.get(context).put(MyConstant.MYSELF_NAME,gson.toJson(result).toString());
    // }
    // return result.getData().getPhotoarr();
    // }
    //

    // /**
    // * 保存用户资料
    // * @param context
    // * @param data
    // */
    // public static void SaveUser(Context context,UserData data){
    // PreferenceUtil.getInstance(context).saveString("uid", data.getUid());
    // PreferenceUtil.getInstance(context).saveString("mobile",
    // data.getMobile());
    // PreferenceUtil.getInstance(context).saveString("userdata_email",
    // data.getUserdata_email());
    // PreferenceUtil.getInstance(context).saveString("userdata_avatar",
    // data.getUserdata_avatar());
    // PreferenceUtil.getInstance(context).saveString("userdata_name",
    // data.getUserdata_name());
    // PreferenceUtil.getInstance(context).saveString("userdata_zhichengdata",
    // data.getUserdata_zhichengdata().toString());
    // PreferenceUtil.getInstance(context).saveString("userdata_company_name",
    // data.getUserdata_company_name());
    // PreferenceUtil.getInstance(context).saveString("userdata_phone",
    // data.getUserdata_phone());
    // PreferenceUtil.getInstance(context).saveString("userdata_company_address",
    // data.getUserdata_company_address());
    // PreferenceUtil.getInstance(context).saveString("userdata_url",
    // data.getUserdata_url());
    // PreferenceUtil.getInstance(context).saveString("userdata_intro",
    // data.getUserdata_intro());
    // }

    public static int getWeeksOfYear(int year) {
	int week = 0;
	int days = 365;
	int day = 0; // 判断是否闰年，闰�?66�?
	if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
	    days = 366;
	}
	// 得到�?���?��天数然后除以7
	day = days % 7 > 0 ? week += 1 : week;
	// 得到余下几天如果有余则周+1，否则不�?
	week += days / 7;
	// 得到多少�?
	return week;
    }

    public static int getdaysOfYear(int year) {
	int days = 365;
	if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
	    days = 366;
	}
	return days;
    }

    /** 用于判断手机号段是否合法 */
    public static boolean isMobileNum(String num) {
	String str = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
	Pattern p = Pattern.compile(str);
	Matcher m = p.matcher(num);
	return m.matches();
    }

    /**
     * 用于判断邮箱是否合法
     */
    public static boolean email_right(String username) {
	// 电子邮件
	String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	Pattern regex = Pattern.compile(check);
	Matcher matcher = regex.matcher(username);
	boolean isMatched = matcher.matches();
	return isMatched;
    }

    /**
     * 获取屏幕大小
     */
//    public static int[] getDisplayScreenResolution() {
//	int screen_w = 0;
//	int screen_h = 0;
//	int ver = Build.VERSION.SDK_INT;
//	if (ver < 13) {
//	    Display display = null;
//	    if (MainActivity.newInstance() != null) {
//		display = MainActivity.newInstance().getWindowManager()
//			.getDefaultDisplay();
//	    }
//	    screen_w = display.getWidth();
//	    screen_h = display.getHeight();
//	} else if (ver >= 13) {
//	    WindowManager wm = null;
//	    if (MainActivity.newInstance() != null) {
//		wm = (WindowManager) MainActivity.newInstance()
//			.getSystemService(Context.WINDOW_SERVICE);
//	    }
//	    // WindowManager wm = (WindowManager)
//	    // MainActivity.newInstance().getSystemService(Context.WINDOW_SERVICE);
//	    Display displays = wm.getDefaultDisplay();
//	    Point size = new Point();
//	    displays.getSize(size);
//	    screen_w = size.x;
//	    screen_h = size.y;
//	}
//	LogUtil.d("屏幕大小=" + screen_w + "," + screen_h);
//	return new int[] { screen_w, screen_h };
//    }

    /**
     * 折叠数字
     */
    public static String SubString(String string) {
	if (string.length() > 3) {
	    return "99+";
	}
	return string;
    }

    /**
     * 去除特殊符号
     */
    public static String getString(String string) {
	return string.replaceAll("&nbsp;", " ").replace("\n", " ")
		.replaceAll("&lt;", "<").replace("&gt", ">")
		.replace("\\s", " ").replace("<br />;", " ");
    }

    /**
     * dip转像�?
     */
    public static int DipToPixels(Context context, float dip) {
	final float SCALE = context.getResources().getDisplayMetrics().density;
	float valueDips = dip;
	int valuePixels = (int) (valueDips * SCALE + 0.5f);
	return valuePixels;
    }

    /**
     * �?��是否有网�?
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkConnected() {
	ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication
		.getApplication()
		.getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
	if (mNetworkInfo != null) {
	    return mNetworkInfo.isAvailable();
	}
	return false;
    }

    /**
     * 
     * unicode 转换�?中文
     * 
     * @return
     */
    public static String decodeUnicode(String theString) {
	char aChar;
	int len = theString.length();
	StringBuffer outBuffer = new StringBuffer(len);
	for (int x = 0; x < len;) {
	    aChar = theString.charAt(x++);
	    if (aChar == '\\') {
		aChar = theString.charAt(x++);
		if (aChar == 'u') {
		    // Read the xxxx
		    int value = 0;
		    for (int i = 0; i < 4; i++) {
			aChar = theString.charAt(x++);
			switch (aChar) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			    value = (value << 4) + aChar - '0';
			    break;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			    value = (value << 4) + 10 + aChar - 'a';
			    break;
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			    value = (value << 4) + 10 + aChar - 'A';
			    break;
			default:
			    throw new IllegalArgumentException(
				    "Malformed   \\uxxxx   encoding.");
			}
		    }
		    outBuffer.append((char) value);
		} else {
		    if (aChar == 't')
			aChar = '\t';
		    else if (aChar == 'r')
			aChar = '\r';
		    else if (aChar == 'n')
			aChar = '\n';
		    else if (aChar == 'f')
			aChar = '\f';
		    outBuffer.append(aChar);
		}
	    } else
		outBuffer.append(aChar);
	}
	return outBuffer.toString();
    }

    public static String readAssertResource(Context context,
	    String strAssertFileName) {
	AssetManager assetManager = context.getAssets();
	String strResponse = "";
	try {
	    InputStream ims = assetManager.open(strAssertFileName);
	    strResponse = getStringFromInputStream(ims);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is) {
	BufferedReader br = null;
	StringBuilder sb = new StringBuilder();
	String line;
	try {
	    br = new BufferedReader(new InputStreamReader(a_is));
	    while ((line = br.readLine()) != null) {
		sb.append(line);
	    }
	} catch (IOException e) {
	} finally {
	    if (br != null) {
		try {
		    br.close();
		} catch (IOException e) {
		}
	    }
	}
	return sb.toString();
    }

	public static  String Html2Text(String html){
		String htmlStr = html; //含html标签的字符串
		String textStr ="";
		try{
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

//			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//			m_script = p_script.matcher(htmlStr);
//			htmlStr = m_script.replaceAll(""); //过滤script标签
//
//			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//			m_style = p_style.matcher(htmlStr);
//			htmlStr = m_style.replaceAll(""); //过滤style标签

			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签

			textStr = htmlStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return textStr;//返回文本字符串
	}
}
