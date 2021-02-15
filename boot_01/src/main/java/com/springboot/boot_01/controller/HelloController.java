package com.springboot.boot_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody
// @Controller
// 合体成这个一个

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String handle01() {
        return "hello springboot";
    }
}
