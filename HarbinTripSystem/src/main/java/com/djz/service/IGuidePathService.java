package com.djz.service;

import com.djz.entity.GuidePath;
import com.djz.entity.GuidePathVO;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/17 -21:16
 */
public interface IGuidePathService {
    /**
     * 获取当前用户的导航记录
     * @param userId
     * @return
     */
     List<GuidePath> getListByUserId(Long userId);

    /**
     * 根据特定条件获取当前用户的导航记录
     * @param guidePath
     * @return
     */
    List<GuidePath> getListByUserIdAndCondition(GuidePathVO guidePath);


    /**
     * 新增导航记录
     * @param guidePath
     * @return
     */
     Boolean addGuidePath(GuidePath guidePath);

    /**
     * 更新导航记录
     * @param guidePath
     * @return
     */
    Boolean updateGuidePath(GuidePath guidePath);

    /**
     * 通过id删除导航记录
     * @param guideId
     * @return
     */
    Boolean removeGuidePath(Long guideId);


    /**
     * 通过ids删除一些导航记录
     * @param guideIds
     * @return
     */
    Boolean removeGuidePaths(List<Long> guideIds);


}
