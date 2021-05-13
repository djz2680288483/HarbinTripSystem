package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @author djz
 * @date 2021/5/13 -8:41
 */
@RestController
public class AjaxController {
    @Autowired
    IUserService userService;

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/login")
    public String getLogin(String name, String pass) {
        String msg = "";
        System.out.println(name);
        User user = userService.getUser(name);
        if (("").equals(user) || user == null) {
            msg = "登录用户名不存在";
            return msg;
        }
        if (!user.getPassword().equals(pass)) {
            msg = "密码错误";
            return msg;
        }
        return msg;
    }

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/checkName")
    public String checkName(String name) {
        String msg = "";
        User user = userService.getUser(name.trim());
        if (user != null) {
            msg = "用户名已存在,请更换注册名";
            return msg;
        }
        return msg;
    }

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/registerUser")
    public String registerUser(String name, String pass, String pass1) {
        String msg = "注册成功";
        if (!pass.trim().equals(pass1.trim())) {
            msg = "前后密码不一致";
            return msg;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(pass);
        user.setCreateTime(new Date());
        int result = userService.addUser(user);
        if (result > 0) {
            return msg;
        }
        msg = "注册失败";
        return msg;
    }
}
