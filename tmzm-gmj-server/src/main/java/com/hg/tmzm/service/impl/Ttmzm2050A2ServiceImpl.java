package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.tmzm.entity.device.drum.Ttmzm2050A2;
import com.hg.tmzm.mapper.device.Ttmzm2050A2Mapper;
import com.hg.tmzm.service.device.ITtmzm2050A2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 转鼓组成信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2050A2ServiceImpl extends BaseServiceImpl<Ttmzm2050A2Mapper, Ttmzm2050A2> implements ITtmzm2050A2Service {

    @Autowired
    private Ttmzm2050A2Mapper ttmzm2050A2Mapper;

    /**
     * 根据转鼓编号查询组成信息列表
     */
    @Override
    public List<Ttmzm2050A2> queryByRotatingDrumNo(String rotatingDrumNo) {
        LambdaQueryWrapper<Ttmzm2050A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2050A2::getRotatingDrumNo, rotatingDrumNo);
        return ttmzm2050A2Mapper.selectList(wrapper);
    }

    /**
     * 批量保存组成信息（先删除后重新创建）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveBatch(String rotatingDrumNo, List<Ttmzm2050A2> records) {
        // 先删除原有组成信息
        LambdaQueryWrapper<Ttmzm2050A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2050A2::getRotatingDrumNo, rotatingDrumNo);
        ttmzm2050A2Mapper.delete(wrapper);

        // 批量插入新组成信息
        if (records != null && !records.isEmpty()) {
            for (Ttmzm2050A2 record : records) {
                record.setRotatingDrumNo(rotatingDrumNo);
                ttmzm2050A2Mapper.insert(record);
            }
            return records.size();
        }
        return 0;
    }
}
