package com.djz.controller;

import com.djz.entity.Guide;
import com.djz.service.IGuideService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author djz
 * @date 2021/5/13 -11:15
 */
@RestController
public class GuideController {
    @Autowired
    IGuideService guideService;

    @RequestMapping("/guide/list")
    List<Guide> listGude(String name) {
        List<Guide> list = guideService.queryGuide(name);
        if (list != null) {
            return list;
        }
        return null;
    }

}
