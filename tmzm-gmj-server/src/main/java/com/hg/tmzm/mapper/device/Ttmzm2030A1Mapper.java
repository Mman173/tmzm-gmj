package com.hg.tmzm.mapper.device;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Ttmzm2030A1Mapper extends BaseMapper<Ttmzm2030A1> {

    /**
     * 查询历史记录（从历史表HTMZM2030A1）
     * TODO: 历史表结构待确认，当前使用主表查询
     */
    @Select("SELECT * FROM TTMZM2030A1 WHERE ID = #{record.id}")
    IPage<Ttmzm2030A1> selectHistoryPage(Page<Ttmzm2030A1> page, @Param("record") Ttmzm2030A1 record);
}
