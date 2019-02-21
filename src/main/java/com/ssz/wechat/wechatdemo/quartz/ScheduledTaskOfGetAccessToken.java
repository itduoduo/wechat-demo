package com.ssz.wechat.wechatdemo.quartz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssz.wechat.wechatdemo.commom.WeChatConfig;
import com.ssz.wechat.wechatdemo.redis.RedisUtil;
import com.ssz.wechat.wechatdemo.util.HttpUtils;

@Component
@Configuration
@EnableScheduling
public class ScheduledTaskOfGetAccessToken {

    private static Logger logger = LoggerFactory.getLogger(ScheduledTaskOfGetAccessToken.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeChatConfig weChatConfig;

    public void getAccessToken() {
        try {
            logger.info("get accessToken begin ......");

            Map<String, String> params = new HashMap<String, String>();
            // 获取token执行体
            params.put("grant_type", "client_credential");
            params.put("appid", weChatConfig.getAppId());
            params.put("secret", weChatConfig.getAppSecret());

            String jsTokenStr;

            jsTokenStr = HttpUtils.sendGet(weChatConfig.getTokenUrl(), params);

            logger.info("get accessToken token: " + jsTokenStr);

            JSONObject jsTokenObj = JSON.parseObject(jsTokenStr);
            String access_token = jsTokenObj.getString("access_token");
            redisUtil.set("access_token", access_token);

            // 获取jsticket的执行体
            params.clear();
            params.put("access_token", access_token);
            params.put("type", "jsapi");
            String jsApiTicket = HttpUtils.sendGet(weChatConfig.getTicketUrl(), params);

            logger.info("get accessToken jsApiTicket: " + jsApiTicket);

            JSONObject jsApiTicketObj = JSON.parseObject(jsApiTicket);
            String ticket = jsApiTicketObj.getString("ticket");
            redisUtil.set("jsapi_ticket", ticket);

            logger.info("get accessToken end ......");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            logger.info("get accessToken error ......");
        }
    }
}
