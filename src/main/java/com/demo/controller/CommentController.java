package com.demo.controller;

import com.demo.common.Result;
import com.demo.pojo.Comment;
import com.demo.pojo.User;
import com.demo.service.CommentService;
import com.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    @RequestMapping("/findOneMusicComment")
    @ResponseBody
    public List<Comment> findOneMusicComment(String mid){
        return commentService.findOneMusicComment(mid);
    }


    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(@RequestBody Comment comment,HttpServletRequest request,HttpSession session) {

        User user=(User)SecurityUtils.getSubject().getPrincipal();

        if(user!=null) {
            System.out.println(user.getNickName());
            comment.setUserId(user.getUserId());
            return commentService.addComment(comment);
        }else{
            return new Result("请登录",500);
        }

    }


    public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
