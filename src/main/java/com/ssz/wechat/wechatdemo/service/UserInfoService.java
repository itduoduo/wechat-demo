package com.ssz.wechat.wechatdemo.service;

import com.ssz.wechat.wechatdemo.domain.UserInfo;

public interface UserInfoService {

    /**
     * @param @param openid
     * @Description: 通过openid获取用户微信信息
     */
    public UserInfo getUserInfo(String openid);
}
