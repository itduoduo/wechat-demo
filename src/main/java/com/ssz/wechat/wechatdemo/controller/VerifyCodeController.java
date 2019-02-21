package com.ssz.wechat.wechatdemo.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssz.wechat.wechatdemo.redis.RedisUtil;
import com.ssz.wechat.wechatdemo.util.ControllerUtils;
import com.ssz.wechat.wechatdemo.util.VerifyCodeUtils;

@Controller
@RequestMapping("/vc")
public class VerifyCodeController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/getVC")
    public void getVC(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        Map<String, Cookie> cookieMap = ControllerUtils.ReadCookieMap(request);
        String vcKey = "";
        if (cookieMap.containsKey("verifyCode")) {
            vcKey = cookieMap.get("verifyCode").getValue();
        } else {
            vcKey = UUID.randomUUID().toString().replaceAll("-", "");
        }

        // 存入redis
        redisUtil.set(vcKey, verifyCode);
        redisUtil.expire(vcKey, 60);

        // 将验证码key，及验证码的图片返回
        Cookie cookie = new Cookie("verifyCode", vcKey);
        response.addCookie(cookie);

        // 生成图片
        int w = 200, h = 80;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/checkVC")
    public String checkVC(HttpServletRequest request, HttpServletResponse response) {

        return "";
    }
}
