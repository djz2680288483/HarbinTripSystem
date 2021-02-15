package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author djz
 * @date 2021/2/15 -22:52
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("可支持测试")
    @GetMapping({"/index", "/"})
    public String getIndex() {
        return "login";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/login")
    public String getLogin(String username, String pass) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("登录用户：" + username);
        System.out.println("登录密码：" + pass);
        if (username.trim() == "") {
            modelAndView.addObject("msg", "用户名或密码不能为空");
            return "login";
        }
        if (username.trim() == "") {
            modelAndView.addObject("msg", "用户名或密码不能为空");
            return "login";
        }
        User user = userService.getUser(username);
        if (("").equals(user) || user == null) {

            modelAndView.addObject("msg", "登录用户名不存在");
            return "login";
        }
        if (!user.getPassword().equals(pass)) {
            modelAndView.addObject("msg", "密码错误");
            return "login";
        }
        return "welcome";
    }

}
