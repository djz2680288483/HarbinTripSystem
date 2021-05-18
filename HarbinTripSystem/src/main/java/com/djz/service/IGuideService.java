package com.djz.service;

import com.djz.entity.Guide;

import java.util.List;

/**
 * @author djz
 * @date 2021/5/13 -11:18
 */
public interface IGuideService {
    /**
     * @param guide
     */
    Boolean addGuide(Guide guide);

    /**
     * 根据用户名查询导航记录
     *
     * @param name
     * @return
     */
    List<Guide> queryGuide(String name);

    /**
     * 根据用户id查询导航记录
     *
     * @param id
     * @return
     */
    Guide selectGuideById(Integer id);
}
