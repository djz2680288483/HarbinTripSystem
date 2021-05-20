package com.djz.controller;

import com.djz.entity.Guide;
import com.djz.service.IGuideService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        ModelMap map = new ModelMap();
        List<Guide> list = guideService.queryGuide(name);
        HttpSession session = request.getSession(true);
        if (list != null) {
            map.put("guides", list);
            session.setAttribute("guides", list);
            return "history";
        }
        return "welcome";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/guide/history")
    public String history(String name, HttpServletRequest request) {
        ModelMap map = new ModelMap();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("guides") != null) {
            session.removeAttribute("guides");
        }
        map.addAttribute("username", name);
        session.setAttribute("username", name);
        List<Guide> list = guideService.queryGuide(name);
        if (!list.isEmpty()) {
            map.addAttribute("guides", list);
            session.setAttribute("guides", list);
            map.addAttribute("newMsg", true);
            session.setAttribute("newMsg", true);
        } else {
            map.addAttribute("newMsg", false);
            session.setAttribute("newMsg", false);
        }
        return "history";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/guide/back")
    public String backAlreadyLogin(String people, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", people);
        ModelMap map = new ModelMap();
        map.addAttribute("username", people);
        return "welcome";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/guide/guideHistoryDetail")
    public String detailGuide(String guideId, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        ModelMap map = new ModelMap();
        Integer id = Integer.parseInt(guideId);
        if (id != null) {
            Guide one = guideService.selectGuideById(id);
            map.addAttribute("one", one);
            session.setAttribute("one", one);
        }
        return "detail";
    }

    @ApiOperation("可支持测试")
    @PostMapping("/guide/history")
    public String detailHistory(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        String name;
        HttpSession session = request.getSession(true);
        name = (String) session.getAttribute("username");
        session.setAttribute("username", name);
        List<Guide> list = guideService.queryGuide(name);
        if (!list.isEmpty()) {
            map.addAttribute("guides", list);
            session.setAttribute("guides", list);
            map.addAttribute("newMsg", true);
            session.setAttribute("newMsg", true);
        } else {
            map.addAttribute("newMsg", false);
            session.setAttribute("newMsg", false);
        }
        return "history";
    }

    @ApiOperation("可支持测试")
    @GetMapping("/guide/deleteHistory")
    public String deleteHistory(String guideId, HttpServletRequest request) {
        Integer id = Integer.parseInt(guideId);
        ModelMap map = new ModelMap();
        Boolean result = guideService.deleteGuide(id);
        HttpSession session = request.getSession(true);
        String name = (String) session.getAttribute("username");
        if (result) {
            List<Guide> list = guideService.queryGuide(name);
            if (!list.isEmpty()) {
                map.addAttribute("username", name);
                map.addAttribute("guides", list);
                map.addAttribute("newMsg", true);
                session.setAttribute("username", name);
                session.setAttribute("guides", list);
                session.setAttribute("newMsg", true);
            } else {
                map.addAttribute("newMsg", false);
                session.setAttribute("newMsg", false);
            }
        }
        return "history";
    }

}
