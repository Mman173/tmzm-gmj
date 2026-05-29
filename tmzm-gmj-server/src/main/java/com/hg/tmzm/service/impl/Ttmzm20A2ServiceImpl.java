package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.plan.Ttmzm20A2;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.mapper.plan.Ttmzm20A2Mapper;
import com.hg.tmzm.service.plan.ITtmzm20A2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 计划排程Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm20A2ServiceImpl extends BaseServiceImpl<Ttmzm20A2Mapper, Ttmzm20A2> implements ITtmzm20A2Service {

    @Autowired
    private Ttmzm20A2Mapper ttmzm20A2Mapper;

    /**
     * 分页查询计划排程列表
     */
    @Override
    public IPage<Ttmzm20A2> queryList(Ttmzm20A2 query, PageQuery pageQuery) {
        Page<Ttmzm20A2> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Ttmzm20A2> wrapper = new LambdaQueryWrapper<>();
        // 根据实际需求添加查询条件
        return ttmzm20A2Mapper.selectPage(page, wrapper);
    }

    /**
     * 根据REC_ID查询单条记录
     */
    @Override
    public Ttmzm20A2 queryById(String recId) {
        LambdaQueryWrapper<Ttmzm20A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm20A2::getRecId, recId);
        return ttmzm20A2Mapper.selectOne(wrapper);
    }

    /**
     * 新增计划排程
     */
    @Override
    public int insertTtmzm20A2(Ttmzm20A2 record) {
        return ttmzm20A2Mapper.insert(record);
    }

    /**
     * 修改计划排程
     */
    @Override
    public int updateTtmzm20A2(Ttmzm20A2 record) {
        return ttmzm20A2Mapper.updateById(record);
    }

    /**
     * 批量删除计划排程
     */
    @Override
    public int deleteTtmzm20A2ByIds(String[] recIds) {
        return ttmzm20A2Mapper.deleteBatchIds(Arrays.asList(recIds));
    }

    /**
     * 确认排程，设置ROLL_STATUS为"1"
     */
    @Override
    public int confirmSchedule(String recId) {
        Ttmzm20A2 record = new Ttmzm20A2();
        record.setRecId(recId);
        record.setRollStatus("1");
        return ttmzm20A2Mapper.updateById(record);
    }

    /**
     * 取消确认，设置ROLL_STATUS为"0"
     */
    @Override
    public int cancelSchedule(String recId) {
        Ttmzm20A2 record = new Ttmzm20A2();
        record.setRecId(recId);
        record.setRollStatus("0");
        return ttmzm20A2Mapper.updateById(record);
    }

    /**
     * 完成计划
     */
    @Override
    public int completeSchedule(String recId) {
        Ttmzm20A2 record = new Ttmzm20A2();
        record.setRecId(recId);
        // 根据业务需求设置完成状态
        return ttmzm20A2Mapper.updateById(record);
    }
}
