package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //跳转页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(){
        //跳转admin.html
        return "admin/admin";
    }

    @RequestMapping("/front")
    public String front(){
        return "front/home_index";
    }
}
