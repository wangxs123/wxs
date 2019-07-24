package com.wxs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import common.entity.UserEntity;
import common.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wxs
 * @date 2019/5/29 10:40
 **/
@RestController
@RequestMapping("/")
public class TestController {
    @Reference(version = "0.0.1")
    UserService userService;

    @GetMapping("/getHello")
    public String getHello(){
        return "hello Dubbo .....";
    }
    @GetMapping("/getUserInfo")
    public List<UserEntity> getUserInfo(){
        List<UserEntity> userList = userService.getAllUserInfo();
        return userList;
    }
}
