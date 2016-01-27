package com.lihonghao.library.base;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lihonghao.library.R;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public class ViewController {
    private IViewHelper mHelper;

    public ViewController(View view) {
        this(new ViewHelper(view));
    }

    public ViewController(ViewHelper helper) {
        super();
        this.mHelper = helper;
    }

    public void showNetWorkError(View.OnClickListener listener) {
        View layout = mHelper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(mHelper.getContext().getResources().getString(R.string.message_no_network));
        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.mipmap.ic_exception);
        if (null != listener) {
            layout.setOnClickListener(listener);
        }
        mHelper.showLayout(layout);
    }

    public void showError(String errorMsg, View.OnClickListener listener) {
        View layout = mHelper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(mHelper.getContext().getResources().getString(R.string.message_error));
        }
        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.mipmap.ic_error);
        if (null != listener) {
            layout.setOnClickListener(listener);
        }
        mHelper.showLayout(layout);
    }

    public void showEmpty(String emptyMsg, View.OnClickListener listener) {
        View layout = mHelper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(mHelper.getContext().getResources().getString(R.string.message_empty));
        }
        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.mipmap.ic_exception);
        if (null != listener) {
            layout.setOnClickListener(listener);
        }
        mHelper.showLayout(layout);
    }

    public void showLoading(String msg) {
        View layout = mHelper.inflate(R.layout.loading);
        if (!TextUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.loading_message);
            textView.setText(msg);
        }
        mHelper.showLayout(layout);
    }

    public void restore() {
        mHelper.restoreView();
    }
}
