package com.lihonghao.framework;

import com.lihonghao.library.GlobalApplication;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/26
 * 描述：
 */
public class DemoApplication extends GlobalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        isDebug(true);
    }
}
