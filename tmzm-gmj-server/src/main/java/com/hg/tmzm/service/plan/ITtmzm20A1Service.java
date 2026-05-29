package com.hg.tmzm.service.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.plan.Ttmzm20A1;
import com.hg.tmzm.common.domain.PageQuery;

/**
 * 生产规格配置Service接口
 *
 * @author hg
 */
public interface ITtmzm20A1Service extends BaseService<Ttmzm20A1> {

    /**
     * 分页查询生产规格配置列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm20A1> queryList(Ttmzm20A1 query, PageQuery pageQuery);

    /**
     * 根据REC_ID查询单条记录
     *
     * @param recId 记录ID
     * @return 生产规格配置
     */
    Ttmzm20A1 queryById(String recId);

    /**
     * 新增生产规格配置
     *
     * @param record 生产规格配置
     * @return 结果
     */
    int insertTtmzm20A1(Ttmzm20A1 record);

    /**
     * 修改生产规格配置
     *
     * @param record 生产规格配置
     * @return 结果
     */
    int updateTtmzm20A1(Ttmzm20A1 record);

    /**
     * 批量删除生产规格配置
     *
     * @param recIds 需要删除的记录ID数组
     * @return 结果
     */
    int deleteTtmzm20A1ByIds(String[] recIds);
}
