package com.djz.service.impl;

import com.djz.entity.Demo;
import com.djz.mapper.DemoMapper;
import com.djz.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/5 -16:22
 */
@Service
public class DemoService implements IDemoService {
    @Autowired
    private DemoMapper mapper;

    @Override
    public List<Demo> getDemoList() {


        List<Demo> list = mapper.selectList(null);
        Long start = System.currentTimeMillis();
        System.out.println("开始时间 " + start);
        list.stream().forEach(demo -> {
            System.out.println(demo);
        });
        Long end = System.currentTimeMillis();
        System.out.println("结束时间 " + end);
        System.out.println("耗费时间： " + (end - start));
        return list;
    }
}
