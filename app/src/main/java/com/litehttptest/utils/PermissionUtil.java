package com.litehttptest.utils;

/*

 * Copyright 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.litehttptest.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that wraps access to the runtime permissions API in M and
 * provides basic helper methods.
 */
public abstract class PermissionUtil {
    public static final int PERMISSIONSREQUEST = 1;
    public static final String PERMISSION_CAMERA = "摄像头";
    public static final String PERMISSION_PHONE = "拨打电话";
    public static final String PERMISSION_LOCATION = "定位";
    public static final String PERMISSION_AUDIO = "录音";
    public static final String PERMISSION_STORAGE = "存储";
    public static final String PERMISSION_SETTING = "舶来汇需要允许修改系统设置的权限，是否去设置";
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    public static int OVERLAY_PERMISSION_REQ_CODE_FOR_PARKING = 1235;
    public static int WRITESETTING_PERMISSION_REQ_CODE = 1236;

    public static String[] Photo_permission = new String[]{
            Manifest.permission.CAMERA,
    };
    public static String[] Photo_permissionMessage = new String[]{
            PermissionUtil.PERMISSION_CAMERA
    };

    /**
     * Check that all given permissions have been granted by verifying that each
     * entry in the given array is of the value
     * {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // Verify that each required permission has been granted, otherwise
        // return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the Activity has access to all given permissions. Always
     * returns true on platforms below M.
     *
     * @see Activity#checkSelfPermission(String)
     */
    public static boolean hasSelfPermission(Activity activity,
                                            String[] permissions) {
        // Below Android M all permissions are granted at install time and are
        // already available.
        if (!isMNC()) {
            return true;
        }

        // Verify that all required permissions have been granted
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static String SwitchToMessage(String permission) {
        if (permission.equals(Manifest.permission.CAMERA)) {
            return PERMISSION_CAMERA;
        } else if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
            return PERMISSION_PHONE;
        } else if (permission
                .equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return PERMISSION_LOCATION;
        } else if (permission
                .equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return PERMISSION_STORAGE;
        } else if (permission.equals(Manifest.permission.RECORD_AUDIO)) {
            return PERMISSION_AUDIO;
        } else if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return PERMISSION_LOCATION;
        }
        return "";
    }

    public static String BuildWarning(List<String> permission) {
        if (permission.size() > 0) {
            // Need Rationale
            String message = "舶来汇需要" + SwitchToMessage(permission.get(0));
            for (int i = 1; i < permission.size(); i++)
                message = message + "、" + SwitchToMessage(permission.get(i));

            return message + "权限";
        }
        return "";

    }

    /**
     * @param activity
     * @param permissions
     * @param dialogMessage 如果请求了被用户标记不再提示的权限，弹出对话框提示用户
     */
    public static boolean CheckSelfPermission(final Activity activity,
                                              String[] permissions, String[] dialogMessage) {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (!addPermission(activity, permissionsList, permission))
                permissionsNeeded.add(dialogMessage[i]);
        }

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "您需要为高德地图授权" + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + "、" + permissionsNeeded.get(i);
                activity.requestPermissions(permissionsList
                                .toArray(new String[permissionsList.size()]),
                        PermissionUtil.PERMISSIONSREQUEST);
                return false;
            }
            activity.requestPermissions(
                    permissionsList.toArray(new String[permissionsList.size()]),
                    PermissionUtil.PERMISSIONSREQUEST);
            return false;
        }
        return true;
    }

    private static void showMessageOKCancel(String message) {
        // new AlertDialog.Builder(activity).setMessage(message)
        // .setPositiveButton(R.string.sure, okListener)
        // .setNegativeButton(R.string.cancel, null).create().show();
        ToastUtil.showToast(MyApplication.getApplication(), message);
    }

    public static boolean CheckWritingPermission(Context context) {
        try {
            if (isMNC() && !Settings.System.canWrite(context)) {
                ToastUtil.showToast(MyApplication.getApplication(), "请在该设置页面勾选，才可以使用路况提醒功能");
                Uri selfPackageUri = Uri.parse("package:"
                        + context.getPackageName());
                Intent intent = new Intent(
                        Settings.ACTION_MANAGE_WRITE_SETTINGS, selfPackageUri);
                context.startActivity(intent);
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    public static boolean CheckWritingPermissionSplash(Context context) {
        try {
            if (isMNC() && !Settings.System.canWrite(context)) {
                Uri selfPackageUri = Uri.parse("package:"
                        + context.getPackageName());
                Intent intent = new Intent(
                        Settings.ACTION_MANAGE_WRITE_SETTINGS, selfPackageUri);
                ((Activity) context).startActivityForResult(intent,
                        WRITESETTING_PERMISSION_REQ_CODE);
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    public static boolean CanWriteSetting(Context context) {
        if (!isMNC()) {
            return true;
        }
        return Settings.System.canWrite(context);
    }

    private static boolean ischecked = false;

//    public static void CheckcanDrawOverlaysPermission(Context context) {
//	if (isMNC() && !Settings.canDrawOverlays(context)) {
//	    Intent intent = new Intent(
//		    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//		    Uri.parse("package:" + context.getPackageName()));
//	    ((Activity) (context)).startActivityForResult(intent,
//		    OVERLAY_PERMISSION_REQ_CODE);
//	} else {
//	    WindowUtils.getInstance().showPopupWindow(context);
//	}
//    }

//    public static void CheckcanDrawOverlaysPermissionShow(Context context) {
//	if (isMNC() && Settings.canDrawOverlays(context)) {
//	    WindowUtils.getInstance().showPopupWindow(context);
//	} else if (!isMNC()) {
//	    WindowUtils.getInstance().showPopupWindow(context);
//	}
//    }

    public static void CheckcanDrawOverlaysPermissionOnly(Context context,
                                                          int requestcode) {
        if (isMNC() && !Settings.canDrawOverlays(context)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.getPackageName()));
            ((Activity) (context)).startActivityForResult(intent, requestcode);
        }
    }

    public static boolean HaveDrawOverlaysPermissionOnly(Context context) {
        if (!isMNC()) {
            return true;
        }
        return Settings.canDrawOverlays(context);
    }

    private static boolean addPermission(Activity activity,
                                         List<String> permissionsList, String permission) {
        if (!hasSelfPermission(activity, permission)) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!activity.shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    /**
     * Returns true if the Activity has access to a given permission. Always
     * returns true on platforms below M.
     *
     * @see Activity#checkSelfPermission(String)
     */
    public static boolean hasSelfPermission(Activity activity, String permission) {
        // Below Android M all permissions are granted at install time and are
        // already available.
        if (!isMNC()) {
            return true;
        }

        return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isMNC() {
    /*
	 * TODO: In the Android M Preview release, checking if the platform is M
	 * is done through the codename, not the version code. Once the API has
	 * been finalised, the following check should be used:
	 */
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.M;

        // return "MNC".equals(Build.VERSION.CODENAME);
    }

}
