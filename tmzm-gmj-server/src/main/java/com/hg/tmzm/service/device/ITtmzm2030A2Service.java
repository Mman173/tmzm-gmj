package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A2;

import java.util.List;

/**
 * 定脱管机架组成信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2030A2Service extends BaseService<Ttmzm2030A2> {

    /**
     * 根据机架编号查询组成信息列表
     *
     * @param standNo 机架编号
     * @return 组成信息列表
     */
    List<Ttmzm2030A2> queryByStandNo(String standNo);

    /**
     * 批量保存组成信息（先删除后重新创建）
     *
     * @param standNo 机架编号
     * @param records 组成信息列表
     * @return 结果
     */
    int saveBatch(String standNo, List<Ttmzm2030A2> records);

    /**
     * 清除某个组件的物料信息
     *
     * @param standNo 机架编号
     * @param serialNo 序列号
     * @return 结果
     */
    int clearMaterial(String standNo, String serialNo);
}
