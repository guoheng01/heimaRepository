package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class QuickStartController {
    @RequestMapping("/quick")
    public String quick(){
        return "hello springboot 访问成功！！！";
    }
}
