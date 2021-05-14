package com.djz.controller;

import com.djz.entity.User;
import com.djz.service.IUserService;
import com.djz.utils.CryptUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
            return "redirect:../";
        }
        String cryptPass = CryptUtils.decode(user.getPassword());
        if (!cryptPass.equals(pass)) {
            modelMap.put("msg", "密码错误");
            return "redirect:../";
        }
        session.setAttribute("user", user.getName());
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

        return "forward:../";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/logout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:../";
    }
}
