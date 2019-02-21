package com.ssz.wechat.wechatdemo.msgtype;

public enum EventType {

    /**
     * 事件类型：subscribe(订阅)
     */
    subscribe,

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    unsubscribe,

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    CLICK,

    /**
     * 事件类型：VIEW(自定义菜单URl视图)
     */
    VIEW,

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    LOCATION,

    /**
     * 事件类型：扫描带参数二维码事件
     */
    SCAN;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
