package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.plan.Ttmzm20A1;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.mapper.plan.Ttmzm20A1Mapper;
import com.hg.tmzm.service.plan.ITtmzm20A1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 生产规格配置Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm20A1ServiceImpl extends BaseServiceImpl<Ttmzm20A1Mapper, Ttmzm20A1> implements ITtmzm20A1Service {

    @Autowired
    private Ttmzm20A1Mapper ttmzm20A1Mapper;

    /**
     * 分页查询生产规格配置列表
     */
    @Override
    public IPage<Ttmzm20A1> queryList(Ttmzm20A1 query, PageQuery pageQuery) {
        Page<Ttmzm20A1> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Ttmzm20A1> wrapper = new LambdaQueryWrapper<>();
        // 根据实际需求添加查询条件
        return ttmzm20A1Mapper.selectPage(page, wrapper);
    }

    /**
     * 根据REC_ID查询单条记录
     */
    @Override
    public Ttmzm20A1 queryById(String recId) {
        LambdaQueryWrapper<Ttmzm20A1> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm20A1::getRecId, recId);
        return ttmzm20A1Mapper.selectOne(wrapper);
    }

    /**
     * 新增生产规格配置
     */
    @Override
    public int insertTtmzm20A1(Ttmzm20A1 record) {
        return ttmzm20A1Mapper.insert(record);
    }

    /**
     * 修改生产规格配置
     */
    @Override
    public int updateTtmzm20A1(Ttmzm20A1 record) {
        return ttmzm20A1Mapper.updateById(record);
    }

    /**
     * 批量删除生产规格配置
     */
    @Override
    public int deleteTtmzm20A1ByIds(String[] recIds) {
        return ttmzm20A1Mapper.deleteBatchIds(Arrays.asList(recIds));
    }
}
