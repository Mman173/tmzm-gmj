package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.device.punchroll.Ttmzm2051A1;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.mapper.device.Ttmzm2051A1Mapper;
import com.hg.tmzm.service.device.ITtmzm2051A1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 穿孔辊主信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2051A1ServiceImpl extends BaseServiceImpl<Ttmzm2051A1Mapper, Ttmzm2051A1> implements ITtmzm2051A1Service {

    @Autowired
    private Ttmzm2051A1Mapper ttmzm2051A1Mapper;

    /**
     * 分页查询穿孔辊主信息列表
     */
    @Override
    public IPage<Ttmzm2051A1> queryList(Ttmzm2051A1 query, PageQuery pageQuery) {
        Page<Ttmzm2051A1> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Ttmzm2051A1> wrapper = new LambdaQueryWrapper<>();
        // 根据实际需求添加查询条件
        return ttmzm2051A1Mapper.selectPage(page, wrapper);
    }

    /**
     * 根据ID查询单条记录
     */
    @Override
    public Ttmzm2051A1 queryById(String id) {
        LambdaQueryWrapper<Ttmzm2051A1> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2051A1::getId, id);
        return ttmzm2051A1Mapper.selectOne(wrapper);
    }

    /**
     * 新增穿孔辊主信息
     */
    @Override
    public int insertTtmzm2051A1(Ttmzm2051A1 record) {
        return ttmzm2051A1Mapper.insert(record);
    }

    /**
     * 修改穿孔辊主信息
     */
    @Override
    public int updateTtmzm2051A1(Ttmzm2051A1 record) {
        return ttmzm2051A1Mapper.updateById(record);
    }

    /**
     * 报废设备
     */
    @Override
    public int scrapDevice(String id, String operator) {
        Ttmzm2051A1 record = new Ttmzm2051A1();
        record.setId(id);
        record.setDeviceStatus("2"); // 报废状态
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2051A1Mapper.updateById(record);
    }

    /**
     * 上线设备（设置DEVICE_STATUS为"1"）
     */
    @Override
    public int onlineDevice(String id, String operator) {
        Ttmzm2051A1 record = new Ttmzm2051A1();
        record.setId(id);
        record.setDeviceStatus("1");
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2051A1Mapper.updateById(record);
    }

    /**
     * 下线设备（更改目的地为装配）
     */
    @Override
    public int offlineDevice(String id, String operator) {
        Ttmzm2051A1 record = new Ttmzm2051A1();
        record.setId(id);
        record.setDeviceStatus("0");
        record.setDestinationStation("assembly"); // 装配工位
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2051A1Mapper.updateById(record);
    }
}
