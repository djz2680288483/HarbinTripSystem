package com.djz.controller;

import com.alibaba.fastjson.JSONObject;
import com.djz.service.IDemoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
