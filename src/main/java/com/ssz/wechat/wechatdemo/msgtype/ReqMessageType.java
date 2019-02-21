package com.ssz.wechat.wechatdemo.msgtype;

/**
 * 接受的消息类型
 *
 * @author 28203
 */
public enum ReqMessageType {

    /**
     * 消息类型：文本
     */
    TEXT,

    /**
     * 消息类型：图片
     */
    IMAGE,

    /**
     * 消息类型：语音
     */
    VOICE,

    /**
     * 消息类型：视频
     */
    VIDEO,

    /**
     * 消息类型：短视频
     */
    SHORTVIDEO,

    /**
     * 消息类型：地理位置
     */
    LOCATION,

    /**
     * 消息类型：链接
     */
    LINK,


    /**
     * 消息类型：推送
     */
    EVENT;


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name().toLowerCase();
    }
}
