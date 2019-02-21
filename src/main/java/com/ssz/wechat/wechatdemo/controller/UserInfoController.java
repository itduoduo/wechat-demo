package com.ssz.wechat.wechatdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.ssz.wechat.wechatdemo.domain.User;
import com.ssz.wechat.wechatdemo.redis.RedisUtil;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private RedisUtil redisUtil;

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String getUser(@PathVariable String id, Model model) {
//        String userJson = (String) redisUtil.get("user:id");
//
//        if (StringUtils.isEmpty(userJson)) {
//            User user = new User(Integer.parseInt(id), "张三" + id, 20 + "", "中国广州");
//            userJson = JSON.toJSONString(user);
//            redisUtil.set("user:" + id, userJson);
//        }
//
//        User user = JSON.parseObject(userJson, User.class);
//
//        logger.info(user.toString());
//
//        model.addAttribute("user", user);
//
//        return "/user/user";
//    }
//
//
//    @RequestMapping("/list")
//    public String userList(Model model) {
//        List<User> userList = new ArrayList<User>();
//        for (int i = 0; i < 10; i++) {
//            userList.add(new User(i, "张三" + i, 20 + i + "", "中国广州"));
//        }
//
//        model.addAttribute("users", userList);
//
//        return "/user/list";
//    }
}
