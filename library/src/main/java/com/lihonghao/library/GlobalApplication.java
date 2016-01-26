package com.lihonghao.library;

import android.app.Application;

import com.lihonghao.library.logger.Logger;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/26
 * 描述：
 */
public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        isDebug(false);
    }

    /**
     * 是否打印日志
     */
    public void isDebug(boolean isDebug) {
        Logger.init(isDebug);
    }
}
