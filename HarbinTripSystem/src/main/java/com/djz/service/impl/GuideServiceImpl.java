package com.djz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djz.common.Constant;
import com.djz.common.DateUtil;
import com.djz.entity.Guide;
import com.djz.entity.GuidePath;
import com.djz.mapper.GuideMapper;
import com.djz.service.IGuideService;
import com.djz.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djz
 * @date 2021/5/13 -11:18
 */
@Service
public class GuideServiceImpl implements IGuideService {
    @Autowired
    private GuideMapper guideMapper;


    @Override
    public Boolean addGuide(Guide guide) {
        if (guide == null || "".equals(guide)) {
            return false;
        }
        int result = guideMapper.insert(guide);
        if (result < Constant.ONE) {
            return false;
        }
        return true;
    }

    @Override
    public List<Guide> queryGuide(String name) {
        QueryWrapper<Guide> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            query.lambda().eq(Guide::getUsername, name);
        }
        query.lambda().orderByDesc(Guide::getCreateTime);
        List<Guide> list = guideMapper.selectList(query);
        if (list != null) {
            return list;
        }
        return null;
    }
}
