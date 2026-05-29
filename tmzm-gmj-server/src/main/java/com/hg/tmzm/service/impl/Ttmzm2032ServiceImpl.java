package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.tmzm.entity.device.frame.Ttmzm2032;
import com.hg.tmzm.mapper.device.Ttmzm2032Mapper;
import com.hg.tmzm.service.device.ITtmzm2032Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定脱管机架加工信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2032ServiceImpl extends BaseServiceImpl<Ttmzm2032Mapper, Ttmzm2032> implements ITtmzm2032Service {

    @Autowired
    private Ttmzm2032Mapper ttmzm2032Mapper;

    /**
     * 根据机架编号查询加工信息列表
     */
    @Override
    public List<Ttmzm2032> queryByStandNo(String standNo) {
        LambdaQueryWrapper<Ttmzm2032> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2032::getStandNo, standNo);
        return ttmzm2032Mapper.selectList(wrapper);
    }

    /**
     * 更新加工参数
     */
    @Override
    public int updateProcessing(Ttmzm2032 record) {
        return ttmzm2032Mapper.updateById(record);
    }

    /**
     * 确认加工完成
     */
    @Override
    public int confirmProcessing(String id, String operator) {
        Ttmzm2032 record = new Ttmzm2032();
        record.setId(id);
        record.setOkFlag("Y");
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2032Mapper.updateById(record);
    }
}
