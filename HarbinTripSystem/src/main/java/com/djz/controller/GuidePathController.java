package com.djz.controller;

import com.djz.entity.GuidePath;
import com.djz.service.IGuidePathService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/17 -21:14
 */
@Controller
@RequestMapping("/guide/")
public class GuidePathController {

    @Autowired
    private IGuidePathService guidePathService;

    @PostMapping("/list")
    @ApiOperation(value = "查询导航记录")
    public String listGuidePath(Long userId, ModelMap modelMap) {

        List<GuidePath> list=guidePathService.getListByUserId(userId);

        modelMap.addAttribute("list",list);
        return "list";
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增导航记录")
    public String addGuidePath(GuidePath guidePath, ModelMap modelMap) {

       Boolean result=guidePathService.addGuidePath(guidePath);

        modelMap.addAttribute("result",result);
        return "add";
    }


    @PostMapping("/remove")
    @ApiOperation(value = "删除导航记录")
    public String removeGuidePath(Long guideId, ModelMap modelMap) {

        Boolean result=guidePathService.removeGuidePath(guideId);

        modelMap.addAttribute("result",result);
        return "remove";
    }



}
