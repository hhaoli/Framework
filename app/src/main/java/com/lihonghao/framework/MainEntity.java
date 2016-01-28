package com.lihonghao.framework;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/28
 * 描述：
 */
public class MainEntity {
    private String title;
    private String desc;

    public MainEntity() {
    }

    public MainEntity(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
