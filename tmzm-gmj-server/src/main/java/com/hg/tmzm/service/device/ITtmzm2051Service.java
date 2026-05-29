package com.hg.tmzm.service.device;

import com.hg.tmzm.entity.device.punchroll.Ttmzm2051;
import com.hg.tmzm.common.domain.BaseService;

public interface ITtmzm2051Service extends BaseService<Ttmzm2051> {
    Ttmzm2051 queryByRotatingDrumNo(String rotatingDrumNo);
    int updateAssembly(Ttmzm2051 record);
}
