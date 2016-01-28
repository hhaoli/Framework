package com.lihonghao.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lihonghao.library.BaseRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mAdapter = new MainAdapter();
        mAdapter.updateList(getList());
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MainEntity>() {
            @Override
            public void onItemClick(View view, MainEntity item, boolean isLongClick) {
                switch (item.getTitle()) {
                    case "三级联动":
                        startActivity(new Intent(MainActivity.this, WheelSelectActivity.class));
                        break;
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<MainEntity> getList() {
        ArrayList<MainEntity> list = new ArrayList<>();
        MainEntity entity = new MainEntity("三级联动", "");
        list.add(entity);
        return list;
    }
}
