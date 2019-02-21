package com.ssz.wechat.wechatdemo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ssz.wechat.wechatdemo.dispatcher.EventDispatcher;
import com.ssz.wechat.wechatdemo.dispatcher.MsgDispatcher;
import com.ssz.wechat.wechatdemo.msgtype.ReqMessageType;
import com.ssz.wechat.wechatdemo.util.MessageUtil;
import com.ssz.wechat.wechatdemo.util.SignUtil;

@RestController
@RequestMapping("wechat")
public class WechatController {

    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    /**
     * 微信验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ac", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
    public String acGet(HttpServletRequest request) {
        System.out.println("wechatController doGet.......");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("weixin signature:" + signature);
        System.out.println("weixin timestamp:" + timestamp);
        System.out.println("weixin nonce:" + nonce);
        System.out.println("weixin echostr:" + echostr);
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        } else {
            return ("deny");
        }
    }

    /**
     * 微信验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ac", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public String acPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("wechatController doPost.......");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        System.out.println("weixin signature:" + signature);
        System.out.println("weixin timestamp:" + timestamp);
        System.out.println("weixin nonce:" + nonce);
        System.out.println("weixin echostr:" + echostr);

        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            logger.info(JSON.toJSONString(map));

            String msgtype = map.get("MsgType");
            if (msgtype.equals(ReqMessageType.EVENT.toString())) {// 接收到的是 事件类型
                return EventDispatcher.processEvent(map);
            } else {
                return MsgDispatcher.processMessage(map);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return "";
    }
}
