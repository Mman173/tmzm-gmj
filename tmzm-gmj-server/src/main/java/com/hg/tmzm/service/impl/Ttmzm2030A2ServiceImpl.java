package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A2;
import com.hg.tmzm.mapper.device.Ttmzm2030A2Mapper;
import com.hg.tmzm.service.device.ITtmzm2030A2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定脱管机架组成信息Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm2030A2ServiceImpl extends BaseServiceImpl<Ttmzm2030A2Mapper, Ttmzm2030A2> implements ITtmzm2030A2Service {

    @Autowired
    private Ttmzm2030A2Mapper ttmzm2030A2Mapper;

    /**
     * 根据机架编号查询组成信息列表
     */
    @Override
    public List<Ttmzm2030A2> queryByStandNo(String standNo) {
        LambdaQueryWrapper<Ttmzm2030A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2030A2::getStandNo, standNo);
        return ttmzm2030A2Mapper.selectList(wrapper);
    }

    /**
     * 批量保存组成信息（先删除后重新创建）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveBatch(String standNo, List<Ttmzm2030A2> records) {
        // 先删除原有组成信息
        LambdaQueryWrapper<Ttmzm2030A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2030A2::getStandNo, standNo);
        ttmzm2030A2Mapper.delete(wrapper);

        // 批量插入新组成信息
        if (records != null && !records.isEmpty()) {
            for (Ttmzm2030A2 record : records) {
                record.setStandNo(standNo);
                ttmzm2030A2Mapper.insert(record);
            }
            return records.size();
        }
        return 0;
    }

    /**
     * 清除某个组件的物料信息
     */
    @Override
    public int clearMaterial(String standNo, String serialNo) {
        Ttmzm2030A2 record = new Ttmzm2030A2();
        // 清空物料相关字段
        record.setAsmatNo(null);
        record.setAsmatName(null);

        LambdaQueryWrapper<Ttmzm2030A2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm2030A2::getStandNo, standNo)
               .eq(Ttmzm2030A2::getSerialNo, serialNo);
        return ttmzm2030A2Mapper.update(record, wrapper);
    }
}
