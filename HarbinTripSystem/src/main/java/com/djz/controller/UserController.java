package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author djz
 * @date 2021/2/15 -22:52
 */
@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("可支持测试")
    @GetMapping({"/index", "/"})
    public String getIndex() {
        return "login";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/login")
    public String getLogin(String username, String pass, Map<String, Object> modelMap) {

        User user = userService.getUser(username);
        if (("").equals(user) || user == null) {

            modelMap.put("msg", "登录用户名不存在");
           return "redirect:../";
        }
        if (!user.getPassword().equals(pass)) {
            modelMap.put("msg", "密码错误");
            return "redirect:../";
        }
        modelMap.put("user", user);
        return "test";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/register")
    public String getRegister() {

        return "register";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/addUser")
    public String addRegister() {

        return "redirect:../";
    }
}
