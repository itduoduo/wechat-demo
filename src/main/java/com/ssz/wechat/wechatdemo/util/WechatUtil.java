package com.ssz.wechat.wechatdemo.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssz.wechat.wechatdemo.commom.WeChatConfig;
import com.ssz.wechat.wechatdemo.domain.UserInfo;
import com.ssz.wechat.wechatdemo.redis.RedisUtil;

@Component
public class WechatUtil {
    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    private static RedisUtil redisUtil;

    private static WeChatConfig wechatConfig;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        WechatUtil.redisUtil = redisUtil;
    }

    @Autowired
    public void setWechatConfig(WeChatConfig wechatConfig) {
        WechatUtil.wechatConfig = wechatConfig;
    }

    /**
     * @param @param openid
     * @Description: 通过openid获取用户微信信息
     */
    public static UserInfo getUserInfo(String openid) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("access_token", WechatUtil.redisUtil.get("access_token").toString()); // 定时器中获取到的token
            params.put("openid", openid); // 需要获取的用户的openid
            params.put("lang", "zh_CN");
            String userInfoStr;

            userInfoStr = HttpUtils.sendGet(WechatUtil.wechatConfig.getOpenIdUserinfoUrl(), params);

            logger.info("getUserInfo userInfo : " + userInfoStr);
            params.clear();
            UserInfo userInfo = JSON.toJavaObject((JSONObject) JSON.parse(userInfoStr), UserInfo.class);
            return userInfo;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
