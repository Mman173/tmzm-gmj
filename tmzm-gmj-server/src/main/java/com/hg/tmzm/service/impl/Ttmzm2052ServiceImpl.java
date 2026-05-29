package com.hg.tmzm.service.impl;

import com.hg.tmzm.entity.device.drum.Ttmzm2052;
import com.hg.tmzm.mapper.device.Ttmzm2052Mapper;
import com.hg.tmzm.service.device.ITtmzm2052Service;
import org.springframework.stereotype.Service;

@Service
public class Ttmzm2052ServiceImpl extends BaseServiceImpl<Ttmzm2052Mapper, Ttmzm2052> implements ITtmzm2052Service {
    @Override
    public Ttmzm2052 queryById(String id) { return getById(id); }
    @Override
    public int insertTtmzm2052(Ttmzm2052 record) { return save(record) ? 1 : 0; }
    @Override
    public int updateTtmzm2052(Ttmzm2052 record) { return updateById(record) ? 1 : 0; }
}
