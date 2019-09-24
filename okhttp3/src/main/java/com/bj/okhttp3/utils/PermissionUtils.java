package com.bj.okhttp3.utils;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;

/**
 *介绍
 如果设备运行的是 Android 6.0（API 级别 23）或更高版本，并且应用的 targetSdkVersion 是 23 或更高版本，则应用在运行时向用户请求权限。
 如果设备运行的是 Android 5.1（API 级别 22）或更低版本，并且应用的 targetSdkVersion 是 22 或更低版本，则系统会在用户安装应用时要求用户授予权限。
 以下是需要单独申请的权限,共分为9组,每组只要有一个权限申请成功了,就默认整组权限都可以使用了
 1、关于日历的权限：
 <uses-permission android:name="android.permission.READ_CALENDAR"/>
 <uses-permission android:name="android.permission.WRITE_CALENDAR"/>
 2、关于相机的权限：
 <uses-permission android:name="android.permission.CAMERA"/>
 3、关于联系人的权限：
 <uses-permission android:name="android.permission.READ_CONTACTS"/>
 <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
 <uses-permission android:name="android.permission.GET_CONTACTS"/>
 4、关于位置的权限：
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 5、关于电话的权限：
 <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 <uses-permission android:name="android.permission.CALL_PHONE"/>
 <uses-permission android:name="android.permission.READ_CALL_LOG"/>
 <uses-permission android:name="android.permission.WRITE_CALL_LOG"/>
 <uses-permission android:name="android.permission.USE_SIP"/>
 <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>
 6、关于传感器的权限：
 <uses-permission android:name="android.permission.BODY_SENSORS"/>
 7、关于短信的权限：
 <uses-permission android:name="android.permission.SEND_SMS"/>
 <uses-permission android:name="android.permission.RECEIVE_SMS"/>
 <uses-permission android:name="android.permission.READ_SMS"/>
 <uses-permission android:name="android.permission.RECEIVE_WAP_PUSH"/>
 <uses-permission android:name="android.permission.RECEIVE_MMS"/>
 <uses-permission android:name="android.permission.READ_CELL_BROADCASTS"/>
 8、关于SD卡的权限
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 9、关于录音的权限
 <uses-permission android:name="android.permission.RECORD_AUDIO"/>
 */
public class PermissionUtils {
    private static int mRequestCode = -1;

    private static OnPermissionListener mOnPermissionListener;

    public interface OnPermissionListener {

        //允许授权
        void onPermissionGranted();
        //不允许授权
        void onPermissionDenied();
    }


    /**1、读取外部存储权限*/
    public static void getPermissionRead (Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE} , listener);
    }

    /**2、拨打电话权限*/
    public static void getPermissionPhone (Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.CALL_PHONE} , listener);
    }

    /**3、拍照权限*/
    public static void getPermissionCamera(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA} , listener);
    }

    /**4、定位权限 */
    public static void getPermissionLocation(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA} , listener);
    }

    /**5、获取传感器权限 */
    public static void getPermissionSensors(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.BODY_SENSORS} , listener);
    }

    /**6、获取短信的权限 */
    public static void getPermissionSMS(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.SEND_SMS} , listener);
    }

    /**7、获取录音的权限 */
    public static void getPermissionRecordAudio(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{RECORD_AUDIO} , listener);
    }

    /**8、获取联系人的权限 */
    public static void getPermissionContactPeople(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_CONTACTS} , listener);
    }

    /**9、获取日历的权限 */
    public static void getPermissionCalendar(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_CALENDAR} , listener);
    }

    /**9、获取日历的权限 */
    public static void getPermissionPhoneState(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_PHONE_STATE} , listener);
    }

    /**10、获取SD卡和照相机权限 */
    public static void getPermissionReadAndCamera(Context context , OnPermissionListener listener){
        requestPermissions(context , 1 , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE ,
                Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO} , listener);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionListener listener) {
        if (context instanceof Activity) {
            mOnPermissionListener = listener;
            List<String> deniedPermissions = getDeniedPermissions(context, permissions);
            if (deniedPermissions.size() > 0) {
                mRequestCode = requestCode;
                ((Activity) context).requestPermissions(deniedPermissions
                        .toArray(new String[deniedPermissions.size()]), requestCode);

            } else {
                if (mOnPermissionListener != null)
                    mOnPermissionListener.onPermissionGranted();
            }
        } else {
            throw new RuntimeException("Context must be an Activity");
        }
    }

    /**
     * 获取请求权限中需要授权的权限
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                if (verifyPermissions(grantResults)) {
                    mOnPermissionListener.onPermissionGranted();
                } else {
                    mOnPermissionListener.onPermissionDenied();
                }
            }
        }
    }

    /**
     * 验证所有权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框(这个提示款适用于点击按钮请求权限的问题)
     * 如果需要强制获取权限，需要自定义对话框
     */
    public static void showTipsDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(context);
                    }
                }).show();
    }
    /**
     * 启动当前应用设置页面
     */
    private static void startAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
