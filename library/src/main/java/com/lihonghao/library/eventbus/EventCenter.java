package com.lihonghao.library.eventbus;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/27
 * 描述：
 */
public class EventCenter<T> {
    private T data;
    private int eventCode = -1;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }

    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return this.eventCode;
    }

    public T getData() {
        return this.data;
    }
}
