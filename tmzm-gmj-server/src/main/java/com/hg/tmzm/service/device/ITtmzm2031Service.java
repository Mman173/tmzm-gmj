package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.frame.Ttmzm2031;

/**
 * 定脱管机架装配信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2031Service extends BaseService<Ttmzm2031> {

    /**
     * 根据机架编号查询装配信息
     *
     * @param standNo 机架编号
     * @return 装配信息
     */
    Ttmzm2031 queryByStandNo(String standNo);

    /**
     * 更新装配参数
     *
     * @param record 装配信息
     * @return 结果
     */
    int updateAssembly(Ttmzm2031 record);
}
