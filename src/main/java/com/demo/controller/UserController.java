package com.demo.controller;

import com.demo.common.Result;
import com.demo.pojo.User;
import com.demo.service.CommentService;
import com.demo.service.UserService;
import com.demo.utils.MD5Creator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.demo.controller.CommentController.ReadCookieMap;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<User> getUserList() {
        return userService.findAllUser();
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(@RequestBody User user){
        SimpleHash simpleHash = (SimpleHash) MD5Creator.MD5CreatePassword(user.getPassword());
        user.setPassword(simpleHash.toString());
        return userService.addUser(user);
    }

    @RequestMapping("/findOneUser")
    @ResponseBody
    public User findOneUser(String id){
        return userService.findOneUser(id);
    }


    @RequestMapping("/login")
    public String login(String name, String password, Model model, HttpServletResponse response, HttpServletRequest request){
        //进行加密
        SimpleHash simpleHash = (SimpleHash) MD5Creator.MD5CreatePassword(password);
        password = simpleHash.toString();

        /**
         * 使用Shrio编写认证
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户对象
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法

        try {
            subject.login(token);
            Cookie cookie = new Cookie("name",name);
            response.addCookie(cookie);
            //更新登录时间
            User user = (User) subject.getPrincipal();
            user.setLastLoginTime(new Date());
            userService.updateUser(user);

            //跳转原网页
            Map<String, Cookie> cookieMap = ReadCookieMap(request);
            if(cookieMap.containsKey("refer")){
                Cookie cookied = (Cookie) cookieMap.get("refer");
                String refer = cookied.getValue();
                if (cookie!=null&&cookie.getValue()!=null&&!cookie.getValue().equals("")) {
                    System.out.println(cookie.getValue());
                    //清除cookie
                    cookie = new Cookie("refer",null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    //跳转
                    response.sendRedirect("/musicDetail?id="+refer);
                }else{
                    response.sendRedirect("/index?name="+name);
                }
            }else{
                response.sendRedirect("/index?name="+name);
            }



        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            //登录失败
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getUserIdByName")
    @ResponseBody
    public String getUserIdByName(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey("name")){
            Cookie cookie = (Cookie)cookieMap.get("name");
            String name = cookie.getValue();
            User user = userService.findByUserName(name);
            return user.getUserId();
        }else{
            response.sendRedirect("/tologin");
            return null;
        }
    }
}
