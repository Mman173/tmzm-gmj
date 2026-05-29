package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.drum.Ttmzm2050A1;
import com.hg.tmzm.common.domain.PageQuery;

/**
 * 转鼓主信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2050A1Service extends BaseService<Ttmzm2050A1> {

    /**
     * 分页查询转鼓主信息列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm2050A1> queryList(Ttmzm2050A1 query, PageQuery pageQuery);

    /**
     * 根据ID查询单条记录
     *
     * @param id 记录ID
     * @return 转鼓主信息
     */
    Ttmzm2050A1 queryById(String id);

    /**
     * 新增转鼓主信息
     *
     * @param record 转鼓主信息
     * @return 结果
     */
    int insertTtmzm2050A1(Ttmzm2050A1 record);

    /**
     * 修改转鼓主信息
     *
     * @param record 转鼓主信息
     * @return 结果
     */
    int updateTtmzm2050A1(Ttmzm2050A1 record);

    /**
     * 报废设备
     *
     * @param id 记录ID
     * @param operator 操作人
     * @return 结果
     */
    int scrapDevice(String id, String operator);

    /**
     * 上线设备（设置DEVICE_STATUS为"1"）
     *
     * @param id 记录ID
     * @param operator 操作人
     * @return 结果
     */
    int onlineDevice(String id, String operator);

    /**
     * 下线设备（更改目的地为装配）
     *
     * @param id 记录ID
     * @param operator 操作人
     * @return 结果
     */
    int offlineDevice(String id, String operator);
}
