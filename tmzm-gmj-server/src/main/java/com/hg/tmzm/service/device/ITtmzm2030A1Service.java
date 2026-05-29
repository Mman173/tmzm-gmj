package com.hg.tmzm.service.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A1;
import com.hg.tmzm.common.domain.PageQuery;

/**
 * 定脱管机架主信息Service接口
 *
 * @author hg
 */
public interface ITtmzm2030A1Service extends BaseService<Ttmzm2030A1> {

    /**
     * 分页查询定脱管机架主信息列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm2030A1> queryList(Ttmzm2030A1 query, PageQuery pageQuery);

    /**
     * 分页查询定脱管机架历史信息列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm2030A1> queryHistoryList(Ttmzm2030A1 query, PageQuery pageQuery);

    /**
     * 根据ID查询单条记录
     *
     * @param id 记录ID
     * @return 定脱管机架主信息
     */
    Ttmzm2030A1 queryById(String id);

    /**
     * 新增定脱管机架主信息
     *
     * @param record 定脱管机架主信息
     * @return 结果
     */
    int insertTtmzm2030A1(Ttmzm2030A1 record);

    /**
     * 修改定脱管机架主信息
     *
     * @param record 定脱管机架主信息
     * @return 结果
     */
    int updateTtmzm2030A1(Ttmzm2030A1 record);

    /**
     * 报废设备（软删除：设置DESTINATION_STATION为"04"）
     *
     * @param id 记录ID
     * @param operator 操作人
     * @return 结果
     */
    int scrapDevice(String id, String operator);

    /**
     * 变更目的地工位
     *
     * @param id 记录ID
     * @param destination 目的地工位
     * @param operator 操作人
     * @return 结果
     */
    int changeDestination(String id, String destination, String operator);
}
