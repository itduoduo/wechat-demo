package com.ssz.wechat.wechatdemo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView view = new ModelAndView();
        view.addObject("exception", e);
        view.addObject("url", req.getRequestURL());
        view.setViewName("error");
        return view;
    }

    @ExceptionHandler(value = BusinessException.class)
    public ModelAndView businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        ModelAndView view = new ModelAndView();
        view.addObject("exception", e);
        view.addObject("url", request.getRequestURL());
        view.setViewName("error");
        return view;
    }
}
