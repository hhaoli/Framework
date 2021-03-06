package com.lihonghao.library.logger;

import android.util.Log;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/26
 * 描述：
 */
public class BaseLog {
    public static void printDefault(int type, String tag, String msg) {
        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(type, tag, sub);
                index += maxLength;
            }
            printSub(type, tag, msg.substring(index, msg.length()));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case Logger.V:
                Log.v(tag, sub);
                break;
            case Logger.D:
                Log.d(tag, sub);
                break;
            case Logger.I:
                Log.i(tag, sub);
                break;
            case Logger.W:
                Log.w(tag, sub);
                break;
            case Logger.E:
                Log.e(tag, sub);
                break;
            case Logger.A:
                Log.wtf(tag, sub);
                break;
        }
    }
}
