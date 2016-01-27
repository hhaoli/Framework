package com.lihonghao.library.base;

import android.app.Activity;

import com.lihonghao.library.logger.Logger;

import java.util.LinkedList;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public class ActManager {
    private static LinkedList<Activity> activityStack;
    private static ActManager instance;

    private ActManager() {
    }

    /**
     * 单一实例
     */
    public static ActManager getInstance() {
        if (instance == null) {
            synchronized (ActManager.class) {
                if (instance == null)
                    instance = new ActManager();
            }
        }
        return instance;
    }

    /**
     * 压栈
     */
    public synchronized void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new LinkedList<>();
        }
        activityStack.add(activity);
    }

    /**
     * 当前Activity
     */
    public synchronized Activity currentActivity() {
        Activity activity = null;
        if (activityStack.size() > 0) {
            activity = activityStack.getLast();
        }
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public synchronized void finishActivity() {
        Activity activity = activityStack.getLast();
        if (null != activity) {
            finishActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public synchronized void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 依据类名退出
     *
     * @param cls Activity的类名
     */
    public synchronized void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public synchronized void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束所有Activity，但保留最后一个
     */
    public synchronized void keepLastOne() {
        for (int i = 0, size = activityStack.size() - 1; i < size; i++) {
            activityStack.get(0).finish();
            activityStack.remove(0);
        }
    }

    /**
     * 堆栈中activity的个数
     *
     * @return
     */
    public synchronized int activitySize() {
        return activityStack.size();
    }

    /**
     * 打印信息
     */
    public synchronized void printActStack() {
        for (int i = 0; i < activityStack.size(); i++) {
            Logger.i("activity-->" + activityStack.get(i).getClass().getSimpleName());
        }
    }
}
