package com.ssz.wechat.wechatdemo.commom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration()
@PropertySource("classpath:cfg/wechat.properties")
public class WeChatConfig {

    @Value("${tokenUrl}")
    private String tokenUrl;

    @Value("${mediaUrl}")
    private String mediaUrl;

    @Value("${openIdUserinfoUrl}")
    private String openIdUserinfoUrl;

    @Value("${ticketUrl}")
    private String ticketUrl;

    @Value("${appId}")
    private String appId;

    @Value("${AppSecret}")
    private String AppSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return AppSecret;
    }

    public void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getOpenIdUserinfoUrl() {
        return openIdUserinfoUrl;
    }

    public void setOpenIdUserinfoUrl(String openIdUserinfoUrl) {
        this.openIdUserinfoUrl = openIdUserinfoUrl;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

}
