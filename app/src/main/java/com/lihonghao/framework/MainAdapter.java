package com.lihonghao.framework;

import android.view.View;
import android.widget.TextView;

import com.lihonghao.library.BaseRecyclerAdapter;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/28
 * 描述：
 */
public class MainAdapter extends BaseRecyclerAdapter<MainEntity> {
    private TextView mTitle;
    private TextView mDesc;

    @Override
    protected int layoutResID() {
        return R.layout.item_main;
    }

    @Override
    protected void initView(View itemView) {
        mTitle = (TextView) itemView.findViewById(R.id.item_main_title);
        mDesc = (TextView) itemView.findViewById(R.id.item_main_desc);
    }

    @Override
    protected void setData(MainEntity data) {
        mTitle.setText(data.getTitle());
        mDesc.setText(data.getDesc());
    }
}
