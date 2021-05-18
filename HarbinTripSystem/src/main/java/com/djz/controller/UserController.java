package com.djz.controller;

import com.djz.entity.Guide;
import com.djz.entity.User;
import com.djz.service.IGuideService;
import com.djz.service.IUserService;
import com.djz.utils.CryptUtils;
import com.sun.javafx.scene.paint.GradientUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
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
        ServletContext application = session.getServletContext();
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

        return "list";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/logout")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "login";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/changePass/{name}")
    public String changePass(String name, HttpSession session) {
        session.setAttribute("username", name);
        return "change";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/user/history/{name}")
    public String history(String name, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("guides") != null) {
            session.removeAttribute("guides");
        }
        name = (String) session.getAttribute("user");
        session.setAttribute("username", name);
        List<Guide> list = guideService.queryGuide(name);
        if (!list.isEmpty()) {
            session.setAttribute("guides", list);
            session.setAttribute("newMsg", true);
        } else {
            session.setAttribute("newMsg", false);
        }
        //return "history";
        return "history";
    }


    @ApiOperation("可支持测试")
    @PostMapping("/user/history")
    public String detailHistory(HttpServletRequest request) {
        String name;
        HttpSession session = request.getSession(true);
        name = (String) session.getAttribute("user");
        session.setAttribute("username", name);
        List<Guide> list = guideService.queryGuide(name);
        if (!list.isEmpty()) {
            session.setAttribute("guides", list);
            session.setAttribute("newMsg", true);
        } else {
            session.setAttribute("newMsg", false);
        }
        //return "history";
        return "history";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/user/back")
    public String backAlreadyLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return "test";
    }

    @ApiOperation("可支持测试")
    @PostMapping("user/guideHistoryDetail")
    public String detailGuide(String guideId, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        System.out.println(guideId);
        Integer id = Integer.parseInt(guideId);
        if (id != null) {
            Guide one = guideService.selectGuideById(id);
            session.setAttribute("guide", one);
        }

        return "detail";
    }
}
