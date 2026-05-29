package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.tmzm.entity.device.punchroll.Ttmzm2051;
import com.hg.tmzm.mapper.device.Ttmzm2051Mapper;
import com.hg.tmzm.service.device.ITtmzm2051Service;
import org.springframework.stereotype.Service;

@Service
public class Ttmzm2051ServiceImpl extends BaseServiceImpl<Ttmzm2051Mapper, Ttmzm2051> implements ITtmzm2051Service {
    @Override
    public Ttmzm2051 queryByRotatingDrumNo(String rotatingDrumNo) {
        LambdaQueryWrapper<Ttmzm2051> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2051::getRotatingDrumNo, rotatingDrumNo);
        return getOne(wrapper);
    }
    @Override
    public int updateAssembly(Ttmzm2051 record) { return updateById(record) ? 1 : 0; }
}
