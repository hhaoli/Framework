package com.lihonghao.library.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public class NetUtils {
    /**
     * 判断网络是否可用
     */
    public static boolean isAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (null != info && info.isAvailable()) {
            return true;
        }
        return false;
    }


    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (null != info && info.isConnected()) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }


    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            return connectivity.getActiveNetworkInfo();
        }
        return null;
    }

    public enum NetType {
        MOBILE, WIFI, NONE
    }

    /**
     * 获取网络类型
     */
    public static NetType getNetworkType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return NetType.MOBILE;
                case ConnectivityManager.TYPE_WIFI:
                    return NetType.WIFI;
            }
        }
        return NetType.NONE;
    }


    /**
     * 打开网络设置页面
     */
    public static void openNetSetting(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        activity.startActivity(intent);
    }

}
