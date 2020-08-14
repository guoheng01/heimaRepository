package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class QueryUserController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping("/queryUser")
    @ResponseBody
    public List queryUser(){
        List<User> users = userMapper.queryUserList();
        return users;
    }
}
