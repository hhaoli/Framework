package com.lihonghao.library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public class ViewHelper implements IViewHelper {
    private View mView;
    private ViewGroup mParentView;
    private int mViewIndex;
    private ViewGroup.LayoutParams mLayoutParams;
    private View mCurrentView;

    public ViewHelper(View view) {
        super();
        this.mView = view;
    }

    private void init() {
        mLayoutParams = mView.getLayoutParams();
        if (mView.getParent() != null) {
            mParentView = (ViewGroup) mView.getParent();
        } else {
            mParentView = (ViewGroup) mView.getRootView().findViewById(android.R.id.content);
        }
        int count = mParentView.getChildCount();
        for (int index = 0; index < count; index++) {
            if (mView == mParentView.getChildAt(index)) {
                mViewIndex = index;
                break;
            }
        }
        mCurrentView = mView;
    }


    @Override
    public View getCurrentLayout() {
        return mCurrentView;
    }

    @Override
    public void restoreView() {
        showLayout(mView);
    }

    @Override
    public void showLayout(View view) {
        if (mParentView == null) {
            init();
        }
        this.mCurrentView = view;
        //如果已经是view,那就不需要再进行替换操作
        if (mParentView.getChildAt(mViewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            mParentView.removeViewAt(mViewIndex);
            mParentView.addView(view, mViewIndex, mLayoutParams);
        }
    }

    @Override
    public View inflate(int layoutResID) {
        return LayoutInflater.from(mView.getContext()).inflate(layoutResID, null);
    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    @Override
    public View getView() {
        return mView;
    }
}
