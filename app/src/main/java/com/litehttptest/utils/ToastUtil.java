package com.litehttptest.utils;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {

	/** 显示Toast. */
	public static final int SHOW_TOAST = 0;

	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param text
	 *            文本
	 */
	public static void showToast(Context context, String text) {
		if (!TextUtils.isEmpty(text)) {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param resId
	 *            文本的资源ID
	 */
	public static void showToast(Context context, int resId) {
		Toast.makeText(context, "" + context.getResources().getText(resId),
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * 描述：在线程中提示文本信息.
	 * 
	 * @param resId
	 *            要提示的字符串资源ID
	 */
	public static void showToastInThread(final Activity context, final int resId) {
		context.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				showToast(context, resId);
			}
		});
	}

	/**
	 * 描述：在线程中提示文本信息.
	 * 
	 * @param toast
	 */
	public static void showToastInThread(final Activity context,
			final String text) {
		context.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				showToast(context, text);
			}
		});
	}

}
