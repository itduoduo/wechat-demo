package com.ssz.wechat.wechatdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 通用页面跳转
     *
     * @param path
     * @param page
     * @return
     */
    @RequestMapping("/page/{page}")
    public String index(@PathVariable String page) {
        return page;
    }

}
