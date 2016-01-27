package com.lihonghao.library.base;

import android.content.Context;
import android.view.View;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public interface IViewHelper {
    View getCurrentLayout();

    void restoreView();

    void showLayout(View view);

    View inflate(int layoutResID);

    Context getContext();

    View getView();
}
