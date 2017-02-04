package com.message.controller;

import com.message.entities.User;
import com.message.service.SmsMessageService;
import com.message.service.UserService;
import com.message.util.CaptchaUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by hpj
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SmsMessageService smsMessageService;
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String home() {
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            return "redirect:/home";
        }
        return "/login";
    }

    @RequestMapping("/")
    public String home_1() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        CaptchaUtils captchaUtils = new CaptchaUtils();
        captchaUtils.getCaptcha(request, response);
    }

    @RequestMapping(value = "/home")
    public String home(Model model){
        Long userId = null;
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN")) {
                userId = user.getId();
            }
        }
        return  "/home";
    }



}
