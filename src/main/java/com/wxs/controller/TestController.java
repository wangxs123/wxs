package com.wxs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxs
 * @date 2019/5/29 10:40
 **/
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/getHello")
    public String getHello(){
        return "hello Dubbo .....";
    }
}
