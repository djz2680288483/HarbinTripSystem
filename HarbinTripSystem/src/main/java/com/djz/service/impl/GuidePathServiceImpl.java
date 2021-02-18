package com.djz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djz.common.Constant;
import com.djz.common.DateUtil;
import com.djz.entity.GuidePath;
import com.djz.entity.GuidePathVO;
import com.djz.mapper.GuidePathMapper;
import com.djz.service.IGuidePathService;
import com.djz.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djz
 * @date 2021/2/17 -21:16
 */
@Service
public class GuidePathServiceImpl implements IGuidePathService {
    @Autowired
    private GuidePathMapper guidePathMapper;


    @Override
    public List<GuidePath> getListByUserId(Long userId) {
        QueryWrapper<GuidePath> query = new QueryWrapper<>();
        query.lambda().eq(GuidePath::getUserId, userId);
        query.lambda().orderByDesc(GuidePath::getCreateTime);
        return guidePathMapper.selectList(query);
    }

    @Override
    public List<GuidePath> getListByUserIdAndCondition(GuidePathVO guidePath) {
        QueryWrapper<GuidePath> query = new QueryWrapper<>();
        //导航价格
        if (guidePath.getGuidePrice() != null) {
            query.lambda().eq(GuidePath::getGuidePrice, guidePath.getGuidePrice());
        }
        //导航方式
        if (guidePath.getGuideType() != null) {
            query.lambda().eq(GuidePath::getGuideType, guidePath.getGuideType());
        }
        //创建日期
        if (guidePath.getStartDate() != null) {
            query.lambda().ge(GuidePath::getCreateTime, guidePath.getStartDate());
        }
        if (guidePath.getEndDate() != null) {
            query.lambda().le(GuidePath::getCreateTime, DateUtil.plusDays(guidePath.getEndDate(), 1));
        }
        query.lambda().orderByDesc(GuidePath::getCreateTime);
        return guidePathMapper.selectList(query);
    }

    @Override
    public Boolean addGuidePath(GuidePath guidePath) {
        if (guidePath == null || "".equals(guidePath)) {
            return false;
        }
        int result = guidePathMapper.insert(guidePath);
        if (result < Constant.ONE) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateGuidePath(GuidePath guidePath) {
        QueryWrapper<GuidePath> query=new QueryWrapper<>();
        query.lambda().eq(GuidePath::getUserId,guidePath.getUserId());
        query.lambda().eq(GuidePath::getGuideType,guidePath.getGuideType());
        int result = guidePathMapper.update(guidePath,query);
        if (result < Constant.ONE) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean removeGuidePath(Long guideId) {
        int result = guidePathMapper.deleteById(guideId);
        if (result < Constant.ONE) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean removeGuidePaths(List<Long> guideIds) {

        if (guideIds.isEmpty()) {
            return false;
        }
        int result = guidePathMapper.deleteBatchIds(guideIds);
        if (result < Constant.ONE) {
            return false;
        }
        return true;
    }
}
