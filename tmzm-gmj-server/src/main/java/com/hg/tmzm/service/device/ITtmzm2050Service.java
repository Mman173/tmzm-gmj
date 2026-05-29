package com.hg.tmzm.service.device;

import com.hg.tmzm.entity.device.drum.Ttmzm2050;
import com.hg.tmzm.common.domain.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;

public interface ITtmzm2050Service extends BaseService<Ttmzm2050> {
    IPage<Ttmzm2050> queryList(Ttmzm2050 query, PageQuery pageQuery);
    Ttmzm2050 queryById(String id);
    int insertTtmzm2050(Ttmzm2050 record);
    int updateTtmzm2050(Ttmzm2050 record);
    int deleteTtmzm2050ByIds(String[] ids);
}
