package com.hg.tmzm.service.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hg.tmzm.common.domain.BaseService;
import com.hg.tmzm.entity.plan.Ttmzm20Cfg;
import com.hg.tmzm.common.domain.PageQuery;

import java.util.List;

/**
 * 匹配配置Service接口
 *
 * @author hg
 */
public interface ITtmzm20CfgService extends BaseService<Ttmzm20Cfg> {

    /**
     * 分页查询匹配配置列表
     *
     * @param query 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<Ttmzm20Cfg> queryList(Ttmzm20Cfg query, PageQuery pageQuery);

    /**
     * 根据规格序号查询配置列表
     *
     * @param seqNo1 规格序号
     * @return 配置列表
     */
    List<Ttmzm20Cfg> queryBySeqNo1(String seqNo1);

    /**
     * 批量保存配置（先删除后重新创建）
     *
     * @param seqNo1 规格序号
     * @param records 配置列表
     * @return 结果
     */
    int saveBatch(String seqNo1, List<Ttmzm20Cfg> records);
}
