package com.djz.controller;

import com.djz.entity.Guide;
import com.djz.entity.User;
import com.djz.service.IGuideService;
import com.djz.service.IUserService;
import com.djz.utils.CryptUtils;
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
    public static final Integer FIVE = 5;
    public static final Integer ZERO = 0;
    @Autowired
    IUserService userService;
    @Autowired
    IGuideService guideService;

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/login")
    public String getLogin(String name, String pass) throws Exception {
        String msg = "";
        User user = userService.getUser(name);
        if (user == null) {
            msg = "登录用户名不存在";
            return msg;
        }
        String cryptPass = CryptUtils.decode(user.getPassword());
        if (!cryptPass.equals(pass)) {
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

        String msg = "";
        if ("".equals(name)) {
            msg = "注册用户名异常";
            return msg;
        }
        if (pass.trim().length() < FIVE) {
            msg = "密码过于简单，请重新设置。密码最少为5位";
            return msg;
        }
        if (!pass.trim().equals(pass1.trim())) {
            msg = "前后密码不一致";
            return msg;
        }
        User user = new User();
        String cryptPass = CryptUtils.encode(pass);
        user.setName(name);
        user.setPassword(cryptPass);
        user.setCreateTime(new Date());
        int result = userService.addUser(user);
        if (result > ZERO) {
            msg = "注册成功";
            return msg;
        }
        msg = "注册失败";
        return msg;
    }

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/addGuideHistory")
    public String addGuidePlace(Guide guide) {
        if (guide != null) {
            guide.setCreateTime(new Date());
            Boolean result = guideService.addGuide(guide);
            if (result) {
                return "本次导航记录已存储数据库";
            }
        }
        return "no";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/ajax/checkPass")
    public String checkPass(String username, String oldPass) {
        String msg = "";
        User user = userService.getUser(username.trim());
        if (user != null) {
            String cryptPass = CryptUtils.decode(user.getPassword());
            if (!cryptPass.equals(oldPass)) {
                msg = "密码错误";
                return msg;
            } else {
                msg = "旧密码正确，请输入新密码";
                return msg;
            }
        }
        msg = "用户异常";
        return msg;
    }


    @ApiOperation("可支持测试")
    @PostMapping("/ajax/updatePass")
    public String updatePass(String name, String oldPass, String pass, String pass1) {
        String msg = "";
        if ("".equals(name)) {
            msg = "用户名获取异常";
            return msg;
        }
        User user = userService.getUser(name);
        if (user != null) {
            String oldPassword = CryptUtils.decode(user.getPassword());
            if ("".equals(oldPass) || !oldPass.equals(oldPassword)) {
                msg = "旧密码验证错误";
                return msg;
            }
            if (pass.trim().length() < FIVE) {
                msg = "密码过于简单，请重新设置。密码最少为5位";
                return msg;
            }
            if (!pass.trim().equals(pass1.trim())) {
                msg = "前后密码不一致";
                return msg;
            }
            String cryptPass = CryptUtils.encode(pass);
            user.setPassword(cryptPass);
            int result = userService.updateUserByName(name, cryptPass);
            if (result > ZERO) {
                msg = "修改成功";
                return msg;
            }
        }
        msg = "修改失败";
        return msg;
    }
}
