package com.hg.tmzm.controller.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.plan.Ttmzm20A1;
import com.hg.tmzm.service.plan.ITtmzm20A1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 生产规格配置 Controller
 */
@Tag(name = "生产规格配置")
@RestController
@RequestMapping("/tmzm/plan/config")
public class Ttmzm20A1Controller extends BaseController {

    @Autowired
    private ITtmzm20A1Service ttmzm20A1Service;

    /**
     * 查询生产规格配置列表
     */
    @Operation(summary = "查询生产规格配置列表")
    @GetMapping("/list")
    public PageResult list(Ttmzm20A1 ttmzm20A1, PageQuery pageQuery) {
        IPage<Ttmzm20A1> page = ttmzm20A1Service.queryList(ttmzm20A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取生产规格配置详细信息")
    @Parameter(name = "recId", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{recId}")
    public AjaxResult getInfo(@PathVariable String recId) {
        return success(ttmzm20A1Service.queryById(recId));
    }

    /**
     * 新增生产规格配置
     */
    @Operation(summary = "新增生产规格配置")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm20A1 ttmzm20A1) {
        return toAjax(ttmzm20A1Service.insertTtmzm20A1(ttmzm20A1));
    }

    /**
     * 修改生产规格配置
     */
    @Operation(summary = "修改生产规格配置")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm20A1 ttmzm20A1) {
        return toAjax(ttmzm20A1Service.updateTtmzm20A1(ttmzm20A1));
    }

    /**
     * 删除生产规格配置
     */
    @Operation(summary = "删除生产规格配置")
    @Parameter(name = "recIds", description = "记录ID列表", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/{recIds}")
    public AjaxResult remove(@PathVariable String[] recIds) {
        return toAjax(ttmzm20A1Service.deleteTtmzm20A1ByIds(recIds));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
