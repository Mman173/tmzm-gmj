package com.hg.tmzm.service.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.plan.Ttmzm20A2;
import com.hg.tmzm.common.domain.PageQuery;

/**
 * 计划排程Service接口
 *
 * @author hg
 */
public interface ITtmzm20A2Service extends BaseService<Ttmzm20A2> {

    /**
     * 分页查询计划排程列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm20A2> queryList(Ttmzm20A2 query, PageQuery pageQuery);

    /**
     * 根据REC_ID查询单条记录
     *
     * @param recId 记录ID
     * @return 计划排程
     */
    Ttmzm20A2 queryById(String recId);

    /**
     * 新增计划排程
     *
     * @param record 计划排程
     * @return 结果
     */
    int insertTtmzm20A2(Ttmzm20A2 record);

    /**
     * 修改计划排程
     *
     * @param record 计划排程
     * @return 结果
     */
    int updateTtmzm20A2(Ttmzm20A2 record);

    /**
     * 批量删除计划排程
     *
     * @param recIds 需要删除的记录ID数组
     * @return 结果
     */
    int deleteTtmzm20A2ByIds(String[] recIds);

    /**
     * 确认排程，设置ROLL_STATUS为"1"
     *
     * @param recId 记录ID
     * @return 结果
     */
    int confirmSchedule(String recId);

    /**
     * 取消确认，设置ROLL_STATUS为"0"
     *
     * @param recId 记录ID
     * @return 结果
     */
    int cancelSchedule(String recId);

    /**
     * 完成计划
     *
     * @param recId 记录ID
     * @return 结果
     */
    int completeSchedule(String recId);
}
