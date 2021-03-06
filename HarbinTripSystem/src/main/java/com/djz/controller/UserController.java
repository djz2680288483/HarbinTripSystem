package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IGuideService;
import com.djz.service.IUserService;
import com.djz.utils.CryptUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

/**
 * @author djz
 * @date 2021/2/15 -22:52
 */
@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IGuideService guideService;

    @ApiOperation("可支持测试")
    @GetMapping({"/index", "/"})
    public String getIndex() {

        return "login";
    }

    @ApiOperation("可支持测试")
    @PostMapping({"/index", "/"})
    public String getIndex1() {

        return "login";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/login")
    public String getLogin(String username, String pass, Map<String, Object> modelMap, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        User user = userService.getUser(username);
        if (user == null) {
            modelMap.put("msg", "登录用户名不存在");
            return "login";
        }
        String cryptPass = CryptUtils.decode(user.getPassword());
        if (!cryptPass.equals(pass)) {
            modelMap.put("msg", "密码错误");
            return "login";
        }
        modelMap.put("username", user.getName());
        session.setAttribute("username", user.getName());
        return "welcome";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/register")
    public String getRegister() {

        return "register";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/logout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "login";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/changePass")
    public String changePass(String name, HttpSession session) {
        session.setAttribute("username", name);
        return "change";
    }

}
