package com.hg.tmzm.service.device;

import com.hg.tmzm.entity.device.rolling.Ttmzm2060A1;
import com.hg.tmzm.common.domain.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;

public interface ITtmzm2060A1Service extends BaseService<Ttmzm2060A1> {
    IPage<Ttmzm2060A1> queryList(Ttmzm2060A1 query, PageQuery pageQuery);
    Ttmzm2060A1 queryById(String id);
    int insertTtmzm2060A1(Ttmzm2060A1 record);
    int updateTtmzm2060A1(Ttmzm2060A1 record);
    int scrapDevice(String id, String operator);
    int offlineDevice(String id, String operator);
}
