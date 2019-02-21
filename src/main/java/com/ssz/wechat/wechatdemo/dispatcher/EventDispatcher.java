package com.ssz.wechat.wechatdemo.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ssz.wechat.wechatdemo.domain.UserInfo;
import com.ssz.wechat.wechatdemo.message.resp.Article;
import com.ssz.wechat.wechatdemo.message.resp.NewsMessage;
import com.ssz.wechat.wechatdemo.message.resp.TextMessage;
import com.ssz.wechat.wechatdemo.msgtype.EventType;
import com.ssz.wechat.wechatdemo.msgtype.RespMessageType;
import com.ssz.wechat.wechatdemo.util.MessageUtil;
import com.ssz.wechat.wechatdemo.util.WechatUtil;

public class EventDispatcher {
    private static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    public static String processEvent(Map<String, String> map) {
        String openid = map.get("FromUserName"); // 用户openid
        String mpid = map.get("ToUserName"); // 公众号原始ID

        String msgType = map.get("Event");
        EventType type;
        try {
            type = EventType.valueOf(msgType);
            switch (type) {
                case subscribe: {
                    logger.info("这是一个subscribe的事件类型...");
                    UserInfo userInfo = WechatUtil.getUserInfo(openid);
                    NewsMessage newMessage = new NewsMessage();
                    Article article = new Article();
                    article.setDescription("欢迎订阅状元家教公众号"); //图文消息的描述
                    article.setPicUrl("http://5b1a4681.ngrok.io/projectImages/welcome.png"); //图文消息图片地址
                    article.setTitle("您好，" + userInfo.getNickname());  //图文消息标题
                    article.setUrl("http://5b1a4681.ngrok.io/page/index");  //图文url链接
                    List<Article> list = new ArrayList<Article>();
                    list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                    newMessage.setArticleCount(list.size());
                    newMessage.setArticles(list);

                    newMessage.setToUserName(openid);
                    newMessage.setFromUserName(mpid);
                    newMessage.setCreateTime(new Date().getTime());
                    newMessage.setMsgType(RespMessageType.NEWS.toString());

//				TextMessage msg = new TextMessage();
//				msg.setToUserName(openid);
//				msg.setFromUserName(mpid);
//				msg.setMsgType(RespMessageType.TEXT.toString());
//				msg.setContent("你好，感谢你订阅我的公众号");
                    return MessageUtil.messageToXml(newMessage);
                }
                case unsubscribe: {
                    logger.info("这是一个subscribe的事件类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("卧槽，你为什么要取消订阅我的公众号");
                    return MessageUtil.messageToXml(msg);
                }
                case CLICK: {
                    logger.info("这是一个CLICK事件类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你触发了一个点击事件CLICK-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case VIEW: {
                    logger.info("这是一个VIEW事件类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你点击菜单跳转链接时的事件推送：VIEW-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case LOCATION: {
                    logger.info("这是一个LOCATION事件类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你上报地理位置事件：LOCATION-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                case SCAN: {
                    logger.info("这是一个SCAN事件类型...");
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你扫描带参数二维码事件：SCAN-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
                default: {
                    logger.info("这是一个未知事件类型..." + msgType);
                    TextMessage msg = new TextMessage();
                    msg.setToUserName(openid);
                    msg.setFromUserName(mpid);
                    msg.setMsgType(RespMessageType.TEXT.toString());
                    msg.setContent("你好，你触发的是：未知类型消息-->" + JSON.toJSONString(map));
                    return MessageUtil.messageToXml(msg);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("这是一个未知事件类型..." + msgType);
        TextMessage msg = new TextMessage();
        msg.setToUserName(openid);
        msg.setFromUserName(mpid);
        msg.setMsgType(RespMessageType.TEXT.toString());
        msg.setContent("你好，你触发的是：未知类型消息-->" + JSON.toJSONString(map));
        return MessageUtil.messageToXml(msg);
    }
}
