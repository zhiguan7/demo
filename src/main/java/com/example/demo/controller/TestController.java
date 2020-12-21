package com.example.demo.controller;

import com.example.demo.pojo.Human;
import com.example.demo.service.HumanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class TestController {

    @Autowired
    private HumanService service;

    @RequestMapping("/t")
    public String test1(Model model) {
        model.addAttribute("msg", "Hello,Thymeleaf");
        return "test";
    }

    @RequestMapping("/human/add")
    public String add() {
        return "/human/add";
    }

    @RequestMapping("/human/update")
    public String update() {
        return "/human/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "/login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/register";
    }

    @RequestMapping("/register")
    public String register(String name, String password){

        Object salt= ByteSource.Util.bytes(name);
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash("SHA-512", password, salt, hashIterations);

        System.out.println(simpleHash);

        service.create(name,simpleHash.toHex());

        return "index";
    }

    @RequestMapping("/login")
    public String login(boolean rememberMe, String name, String password, HttpSession session, Model model) {

        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        token.setRememberMe(rememberMe);

        if(!currentUser.isAuthenticated()){
            try {
                currentUser.login(token);
                Human human=(Human)SecurityUtils.getSubject().getPrincipal();
                session.setAttribute("user",human);
                return "index";
            } catch (UnknownAccountException e) {
                model.addAttribute("msg", "用户名不存在");
                return "login";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("msg", "密码错误");
                return "login";
            }
        }else {
            model.addAttribute("msg", "用户已登陆");
            return "login";
        }
    }
}
