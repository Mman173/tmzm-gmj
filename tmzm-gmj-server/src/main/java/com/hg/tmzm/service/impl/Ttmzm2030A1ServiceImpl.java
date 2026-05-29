package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A1;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.mapper.device.Ttmzm2030A1Mapper;
import com.hg.tmzm.service.device.ITtmzm2030A1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 定脱管机架主信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2030A1ServiceImpl extends BaseServiceImpl<Ttmzm2030A1Mapper, Ttmzm2030A1> implements ITtmzm2030A1Service {

    @Autowired
    private Ttmzm2030A1Mapper ttmzm2030A1Mapper;

    /**
     * 分页查询定脱管机架主信息列表
     */
    @Override
    public IPage<Ttmzm2030A1> queryList(Ttmzm2030A1 query, PageQuery pageQuery) {
        Page<Ttmzm2030A1> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Ttmzm2030A1> wrapper = new LambdaQueryWrapper<>();
        // 根据实际需求添加查询条件
        return ttmzm2030A1Mapper.selectPage(page, wrapper);
    }

    /**
     * 分页查询定脱管机架历史信息列表
     */
    @Override
    public IPage<Ttmzm2030A1> queryHistoryList(Ttmzm2030A1 query, PageQuery pageQuery) {
        Page<Ttmzm2030A1> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        // 使用历史表HTMZM2030A1进行查询
        // 需要在Mapper中定义对应的方法
        return ttmzm2030A1Mapper.selectHistoryPage(page, query);
    }

    /**
     * 根据ID查询单条记录
     */
    @Override
    public Ttmzm2030A1 queryById(String id) {
        LambdaQueryWrapper<Ttmzm2030A1> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2030A1::getId, id);
        return ttmzm2030A1Mapper.selectOne(wrapper);
    }

    /**
     * 新增定脱管机架主信息
     */
    @Override
    public int insertTtmzm2030A1(Ttmzm2030A1 record) {
        return ttmzm2030A1Mapper.insert(record);
    }

    /**
     * 修改定脱管机架主信息
     */
    @Override
    public int updateTtmzm2030A1(Ttmzm2030A1 record) {
        return ttmzm2030A1Mapper.updateById(record);
    }

    /**
     * 报废设备（软删除：设置DESTINATION_STATION为"04"）
     */
    @Override
    public int scrapDevice(String id, String operator) {
        Ttmzm2030A1 record = new Ttmzm2030A1();
        record.setId(id);
        record.setDestinationStation("04");
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2030A1Mapper.updateById(record);
    }

    /**
     * 变更目的地工位
     */
    @Override
    public int changeDestination(String id, String destination, String operator) {
        Ttmzm2030A1 record = new Ttmzm2030A1();
        record.setId(id);
        record.setDestinationStation(destination);
        record.setRecRevisor(operator);
        record.setRecReviseTime(LocalDateTime.now());
        return ttmzm2030A1Mapper.updateById(record);
    }
}
