package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.entity.device.drum.Ttmzm2050;
import com.hg.tmzm.mapper.device.Ttmzm2050Mapper;
import com.hg.tmzm.service.device.ITtmzm2050Service;
import org.springframework.stereotype.Service;

@Service
public class Ttmzm2050ServiceImpl extends BaseServiceImpl<Ttmzm2050Mapper, Ttmzm2050> implements ITtmzm2050Service {
    @Override
    public IPage<Ttmzm2050> queryList(Ttmzm2050 query, PageQuery pageQuery) {
        LambdaQueryWrapper<Ttmzm2050> wrapper = new LambdaQueryWrapper<>();
        // Add conditions based on non-null query fields
        return page(pageQuery.buildPage(), wrapper);
    }

    @Override
    public Ttmzm2050 queryById(String id) {
        return getById(id);
    }

    @Override
    public int insertTtmzm2050(Ttmzm2050 record) {
        return save(record) ? 1 : 0;
    }

    @Override
    public int updateTtmzm2050(Ttmzm2050 record) {
        return updateById(record) ? 1 : 0;
    }

    @Override
    public int deleteTtmzm2050ByIds(String[] ids) {
        return removeByIds(java.util.Arrays.asList(ids)) ? 1 : 0;
    }
}
