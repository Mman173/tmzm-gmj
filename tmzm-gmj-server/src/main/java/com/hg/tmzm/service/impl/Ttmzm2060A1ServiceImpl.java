package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.constants.DestinationConstants;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.entity.device.rolling.Ttmzm2060A1;
import com.hg.tmzm.mapper.device.Ttmzm2060A1Mapper;
import com.hg.tmzm.service.device.ITtmzm2060A1Service;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Ttmzm2060A1ServiceImpl extends BaseServiceImpl<Ttmzm2060A1Mapper, Ttmzm2060A1> implements ITtmzm2060A1Service {
    @Override
    public IPage<Ttmzm2060A1> queryList(Ttmzm2060A1 query, PageQuery pageQuery) {
        LambdaQueryWrapper<Ttmzm2060A1> wrapper = new LambdaQueryWrapper<>();
        if (query.getDeviceType() != null && !query.getDeviceType().trim().isEmpty()) {
            wrapper.eq(Ttmzm2060A1::getDeviceType, query.getDeviceType());
        }
        return page(pageQuery.buildPage(), wrapper);
    }

    @Override
    public Ttmzm2060A1 queryById(String id) { return getById(id); }

    @Override
    public int insertTtmzm2060A1(Ttmzm2060A1 record) { return save(record) ? 1 : 0; }

    @Override
    public int updateTtmzm2060A1(Ttmzm2060A1 record) { return updateById(record) ? 1 : 0; }

    @Override
    public int scrapDevice(String id, String operator) {
        Ttmzm2060A1 record = new Ttmzm2060A1();
        record.setId(id);
        record.setDestinationStation(DestinationConstants.SCRAP);
        record.setDeviceStatus("0");
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return updateById(record) ? 1 : 0;
    }

    @Override
    public int offlineDevice(String id, String operator) {
        Ttmzm2060A1 record = new Ttmzm2060A1();
        record.setId(id);
        record.setDeviceStatus("0");
        record.setDestinationStation(DestinationConstants.ASSEMBLY);
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return updateById(record) ? 1 : 0;
    }
}
