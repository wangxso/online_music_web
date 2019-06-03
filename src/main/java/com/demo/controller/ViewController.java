package com.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/musicDetail")
    public String getMusicDetail(){
        return "musicDetail";
    }
    @RequestMapping("/allMusic")
    public String getAllMusic(){
        return "allMusic";
    }
    @RequestMapping("/tologin")
    public String getLogin(String refer, HttpServletResponse response){
        Cookie cookie = new Cookie("refer",refer);
        response.addCookie(cookie);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("refer",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "login";
    }




}
