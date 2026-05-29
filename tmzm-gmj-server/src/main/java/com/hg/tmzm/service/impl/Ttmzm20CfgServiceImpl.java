package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.plan.Ttmzm20Cfg;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.mapper.plan.Ttmzm20CfgMapper;
import com.hg.tmzm.service.plan.ITtmzm20CfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 匹配配置Service实现
 *
 * @author hg
 */
@Service
public class Ttmzm20CfgServiceImpl extends BaseServiceImpl<Ttmzm20CfgMapper, Ttmzm20Cfg> implements ITtmzm20CfgService {

    @Autowired
    private Ttmzm20CfgMapper ttmzm20CfgMapper;

    /**
     * 分页查询匹配配置列表
     */
    @Override
    public IPage<Ttmzm20Cfg> queryList(Ttmzm20Cfg query, PageQuery pageQuery) {
        Page<Ttmzm20Cfg> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        LambdaQueryWrapper<Ttmzm20Cfg> wrapper = new LambdaQueryWrapper<>();
        // 根据实际需求添加查询条件
        return ttmzm20CfgMapper.selectPage(page, wrapper);
    }

    /**
     * 根据规格序号查询配置列表
     */
    @Override
    public List<Ttmzm20Cfg> queryBySeqNo1(String seqNo1) {
        LambdaQueryWrapper<Ttmzm20Cfg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm20Cfg::getSeqNo1, seqNo1);
        return ttmzm20CfgMapper.selectList(wrapper);
    }

    /**
     * 批量保存配置（先删除后重新创建）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveBatch(String seqNo1, List<Ttmzm20Cfg> records) {
        // 先删除原有配置
        LambdaQueryWrapper<Ttmzm20Cfg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Ttmzm20Cfg::getSeqNo1, seqNo1);
        ttmzm20CfgMapper.delete(wrapper);

        // 批量插入新配置
        if (records != null && !records.isEmpty()) {
            for (Ttmzm20Cfg record : records) {
                record.setSeqNo1(seqNo1);
                ttmzm20CfgMapper.insert(record);
            }
            return records.size();
        }
        return 0;
    }
}
