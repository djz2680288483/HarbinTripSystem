package com.djz.controller;
import com.djz.entity.Guide;
import com.djz.service.IGuideService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author djz
 * @date 2021/5/13 -11:15
 */
@Controller
public class GuideController {
    @Autowired
    IGuideService guideService;

    @RequestMapping("/guide/list")
    public String listGude(String name, HttpServletRequest request) {
        List<Guide> list = guideService.queryGuide(name);
        HttpSession session = request.getSession(true);
        if (list != null) {
            session.setAttribute("guides", list);
            return "history";
        }
        return "test";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/guide/history/{name}")
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
        return "history11";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/guide/back")
    public String backAlreadyLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return "test";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/guide/guideHistoryDetail")
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
