package com.ssz.wechat.wechatdemo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssz.wechat.wechatdemo.commom.WeChatConfig;
import com.ssz.wechat.wechatdemo.domain.UserInfo;
import com.ssz.wechat.wechatdemo.redis.RedisUtil;
import com.ssz.wechat.wechatdemo.util.HttpUtils;

@Service
public class UserInfoServiceImpl {


    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * @param @param openid
     * @Description: 通过openid获取用户微信信息
     */
    public UserInfo getUserInfo(String openid) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("access_token", redisUtil.get("access_token").toString()); // 定时器中获取到的token
            params.put("openid", openid); // 需要获取的用户的openid
            params.put("lang", "zh_CN");
            String userInfoStr;

            userInfoStr = HttpUtils.sendGet(weChatConfig.getOpenIdUserinfoUrl(), params);

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
