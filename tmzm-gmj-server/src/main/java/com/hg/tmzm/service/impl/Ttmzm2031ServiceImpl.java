package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.tmzm.entity.device.frame.Ttmzm2031;
import com.hg.tmzm.mapper.device.Ttmzm2031Mapper;
import com.hg.tmzm.service.device.ITtmzm2031Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定脱管机架装配信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2031ServiceImpl extends BaseServiceImpl<Ttmzm2031Mapper, Ttmzm2031> implements ITtmzm2031Service {

    @Autowired
    private Ttmzm2031Mapper ttmzm2031Mapper;

    /**
     * 根据机架编号查询装配信息
     */
    @Override
    public Ttmzm2031 queryByStandNo(String standNo) {
        LambdaQueryWrapper<Ttmzm2031> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2031::getStandNo, standNo);
        return ttmzm2031Mapper.selectOne(wrapper);
    }

    /**
     * 更新装配参数
     */
    @Override
    public int updateAssembly(Ttmzm2031 record) {
        return ttmzm2031Mapper.updateById(record);
    }
}
