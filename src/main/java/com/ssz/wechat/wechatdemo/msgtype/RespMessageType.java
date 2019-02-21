package com.ssz.wechat.wechatdemo.msgtype;

/**
 * 回复消息类型
 *
 * @author ssz
 */
public enum RespMessageType {

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
     * 消息类型：音乐
     */
    MUSIC,

    /**
     * 消息类型：图文
     */
    NEWS;


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name().toLowerCase();
    }
}
