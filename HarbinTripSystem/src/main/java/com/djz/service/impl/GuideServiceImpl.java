package com.djz.service.impl;

import com.djz.common.Constant;
import com.djz.entity.Guide;
import com.djz.mapper.GuideMapper;
import com.djz.service.IGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
