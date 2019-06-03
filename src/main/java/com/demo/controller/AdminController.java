package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @RequestMapping("/index")
    public String getIndex(){
        return "/admin/index";
    }
    @RequestMapping("/content")
    public String getContent(){
        return "/admin/content";
    }
    @RequestMapping("/home")
    public String getHome(){
        return "/admin/home";
    }
    @RequestMapping("/music")
    public String getMusic(){
        return "/admin/music";
    }

    @RequestMapping("/userEdit")
    public String getUserEdit(){
        return "/admin/userEdit";
    }
}
