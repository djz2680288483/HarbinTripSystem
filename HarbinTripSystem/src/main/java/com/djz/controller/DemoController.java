package com.djz.controller;

import com.alibaba.fastjson.JSONObject;
import com.djz.entity.Demo;
import com.djz.service.IDemoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/5 -16:20
 */
@Controller
@RequestMapping("/demo/")
public class DemoController {
    @Autowired
    IDemoService service;

    @GetMapping("/test")
    @ResponseBody
    public String demoList() {
        String objectsJSON = JSONObject.toJSONString(service.getDemoList());
        return objectsJSON;
    }

    @ApiOperation("可支持测试")
    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }

}
