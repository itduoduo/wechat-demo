package com.ssz.wechat.wechatdemo.dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ssz.wechat.wechatdemo.controller.WechatController;
import com.ssz.wechat.wechatdemo.domain.UserInfo;
import com.ssz.wechat.wechatdemo.message.resp.Article;
import com.ssz.wechat.wechatdemo.message.resp.TextMessage;
import com.ssz.wechat.wechatdemo.msgtype.ReqMessageType;
import com.ssz.wechat.wechatdemo.msgtype.RespMessageType;
import com.ssz.wechat.wechatdemo.util.MessageUtil;
import com.ssz.wechat.wechatdemo.util.WechatUtil;

public class MsgDispatcher {
    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    public static String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); // 用户openid
        String mpid = map.get("ToUserName"); // 公众号原始ID

        String msgType = map.get("MsgType").toUpperCase();
        ReqMessageType type;
        try {
            type = ReqMessageType.valueOf(msgType);
            switch (type) {
                case TEXT: {
                    logger.info("这是一个TEXT消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：" + map.get("Content"));
                    return MessageUtil.messageToXml(msg);
                }
                case IMAGE: {
                    logger.info("这是一个IMAGE消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：IMAGE-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case VOICE: {
                    logger.info("这是一个VOICE消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：VOICE-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case VIDEO: {
                    logger.info("这是一个VIDEO消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：VIDEO-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case LOCATION: {
                    logger.info("这是一个LOCATION消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：LOCATION-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case LINK: {
                    logger.info("这是一个LINK消息类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：LINK-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                default: {
                    logger.info("这是一个未知消息类型..." + msgType);
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你发送的消息是：未知类型消息-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("这是一个未知消息类型..." + msgType);
        TextMessage msg = new TextMessage();
        msg.setToUserName(openid);
        msg.setFromUserName(mpid);
        msg.setMsgType(RespMessageType.TEXT.toString());
        msg.setContent("你好，你发送的消息是：未知类型消息-->" + JSON.toJSONString(map));
        return MessageUtil.messageToXml(msg);
    }
}
