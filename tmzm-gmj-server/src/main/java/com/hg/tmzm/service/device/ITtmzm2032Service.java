package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.frame.Ttmzm2032;

import java.util.List;

/**
 * 定脱管机架加工信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2032Service extends BaseService<Ttmzm2032> {

    /**
     * 根据机架编号查询加工信息列表
     *
     * @param standNo 机架编号
     * @return 加工信息列表
     */
    List<Ttmzm2032> queryByStandNo(String standNo);

    /**
     * 更新加工参数
     *
     * @param record 加工信息
     * @return 结果
     */
    int updateProcessing(Ttmzm2032 record);

    /**
     * 确认加工完成
     *
     * @param id 记录ID
     * @param operator 操作人
     * @return 结果
     */
    int confirmProcessing(String id, String operator);
}
