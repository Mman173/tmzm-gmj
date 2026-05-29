package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.drum.Ttmzm2050A2;

import java.util.List;

/**
 * 转鼓组成信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2050A2Service extends BaseService<Ttmzm2050A2> {

    /**
     * 根据转鼓编号查询组成信息列表
     *
     * @param rotatingDrumNo 转鼓编号
     * @return 组成信息列表
     */
    List<Ttmzm2050A2> queryByRotatingDrumNo(String rotatingDrumNo);

    /**
     * 批量保存组成信息（先删除后重新创建）
     *
     * @param rotatingDrumNo 转鼓编号
     * @param records 组成信息列表
     * @return 结果
     */
    int saveBatch(String rotatingDrumNo, List<Ttmzm2050A2> records);
}
