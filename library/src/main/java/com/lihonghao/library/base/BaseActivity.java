package com.lihonghao.library.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.lihonghao.library.eventbus.EventCenter;
import com.lihonghao.library.net.NetChangeObserver;
import com.lihonghao.library.net.NetStateReceiver;
import com.lihonghao.library.net.NetUtils;

import de.greenrobot.event.EventBus;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/26
 * 描述：
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected NetChangeObserver mNetChangeObserver;
    private ViewController mViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isBindEventBus()) {
            EventBus.getDefault().register(this);
        }

        // 添加Activity到堆栈
        ActManager.getInstance().addActivity(this);

        if (layoutResID() != 0) {
            setContentView(layoutResID());
        } else {
            throw new IllegalArgumentException("必须返回布局Id");
        }

        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnect();
            }
        };
        NetStateReceiver.registerObserver(mNetChangeObserver);
        initView();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (null != getLoadingTargetView()) {
            mViewController = new ViewController(getLoadingTargetView());
        }
    }

    @Override
    public void finish() {
        super.finish();
        ActManager.getInstance().finishActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        if (isBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        // 结束Activity从堆栈中移除
        ActManager.getInstance().finishActivity(this);
    }

    /**
     * 是否开启加载布局
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mViewController) {
            throw new IllegalArgumentException("必须返回一个加载布局");
        }
        if (toggle) {
            mViewController.showLoading(msg);
        } else {
            mViewController.restore();
        }
    }

    /**
     * 是否开启空布局
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener listener) {
        if (null == mViewController) {
            throw new IllegalArgumentException("必须返回一个空布局");
        }
        if (toggle) {
            mViewController.showEmpty(msg, listener);
        } else {
            mViewController.restore();
        }
    }

    /**
     * 是否开启错误布局
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener listener) {
        if (null == mViewController) {
            throw new IllegalArgumentException("必须返回一个错误布局");
        }
        if (toggle) {
            mViewController.showError(msg, listener);
        } else {
            mViewController.restore();
        }
    }

    /**
     * 是否开启网络错误布局
     */
    protected void toggleShowNetworkError(boolean toggle, View.OnClickListener listener) {
        if (null == mViewController) {
            throw new IllegalArgumentException("必须返回一个错误布局");
        }
        if (toggle) {
            mViewController.showNetWorkError(listener);
        } else {
            mViewController.restore();
        }
    }

    public void onEventMainThread(EventCenter center) {
        if (null != center) {
            onEventCenter(center);
        }
    }

    protected abstract boolean isBindEventBus();

    protected abstract int layoutResID();

    protected abstract void initView();

    protected abstract void onNetworkConnected(NetUtils.NetType type);

    protected abstract void onNetworkDisConnect();

    protected abstract View getLoadingTargetView();

    protected abstract void onEventCenter(EventCenter center);

    public void finishAllActivity() {
        ActManager.getInstance().finishAllActivity();
    }
}
