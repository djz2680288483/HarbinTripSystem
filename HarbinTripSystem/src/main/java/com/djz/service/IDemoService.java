package com.djz.service;

import com.djz.entity.Demo;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/5 -16:21
 */
public interface IDemoService {
    /**
     * 获取demo 集合
     * @return
     */
    List<Demo> getDemoList();
}
