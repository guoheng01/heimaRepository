package com.itheima.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ConfigurationProperties(prefix = "person")
public class QuickStartController {
    private String name;
    private Integer age;
    private String addr;
   @RequestMapping("/person")
   @ResponseBody
    public String quick(){
       return "SpringBoot访问成功！！！" + "name="+ name + "; age="+ age + "; addr="+addr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
