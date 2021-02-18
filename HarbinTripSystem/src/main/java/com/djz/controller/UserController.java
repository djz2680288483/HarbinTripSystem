package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getLogin(String username, String pass, ModelMap modelMap) {
//        System.out.println("登录用户：" + username);
//        System.out.println("登录密码：" + pass);
        if (username.trim() == "") {
            modelMap.addAttribute("msg", "用户名或密码不能为空");
            return "login";
        }
        if (username.trim() == "") {
            modelMap.addAttribute("msg", "用户名或密码不能为空");
            return "login";
        }
        User user = userService.getUser(username);
        if (("").equals(user) || user == null) {

            modelMap.addAttribute("msg", "登录用户名不存在");
            return "login";
        }
        if (!user.getPassword().equals(pass)) {
            modelMap.addAttribute("msg", "密码错误");
            return "login";
        }
        modelMap.addAttribute("user",user);
        System.out.println("==================="+user);
        return "welcome1";
    }

}
