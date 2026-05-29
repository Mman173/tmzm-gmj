package com.hg.tmzm.service.device;

import com.hg.tmzm.entity.device.drum.Ttmzm2052;
import com.hg.tmzm.common.domain.BaseService;

public interface ITtmzm2052Service extends BaseService<Ttmzm2052> {
    Ttmzm2052 queryById(String id);
    int insertTtmzm2052(Ttmzm2052 record);
    int updateTtmzm2052(Ttmzm2052 record);
}
