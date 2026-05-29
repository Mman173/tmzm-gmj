package com.hg.tmzm.controller.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.plan.Ttmzm20A2;
import com.hg.tmzm.service.plan.ITtmzm20A2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 计划排程 Controller
 */
@Tag(name = "计划排程")
@RestController
@RequestMapping("/tmzm/plan/schedule")
public class Ttmzm20A2Controller extends BaseController {

    @Autowired
    private ITtmzm20A2Service ttmzm20A2Service;

    /**
     * 查询计划排程列表
     */
    @Operation(summary = "查询计划排程列表")
    @GetMapping("/list")
    public PageResult list(Ttmzm20A2 ttmzm20A2, PageQuery pageQuery) {
        IPage<Ttmzm20A2> page = ttmzm20A2Service.queryList(ttmzm20A2, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取计划排程详细信息")
    @Parameter(name = "recId", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{recId}")
    public AjaxResult getInfo(@PathVariable String recId) {
        return success(ttmzm20A2Service.queryById(recId));
    }

    /**
     * 新增计划排程
     */
    @Operation(summary = "新增计划排程")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm20A2 ttmzm20A2) {
        return toAjax(ttmzm20A2Service.insertTtmzm20A2(ttmzm20A2));
    }

    /**
     * 修改计划排程
     */
    @Operation(summary = "修改计划排程")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm20A2 ttmzm20A2) {
        return toAjax(ttmzm20A2Service.updateTtmzm20A2(ttmzm20A2));
    }

    /**
     * 删除计划排程
     */
    @Operation(summary = "删除计划排程")
    @Parameter(name = "recIds", description = "记录ID列表", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/{recIds}")
    public AjaxResult remove(@PathVariable String[] recIds) {
        return toAjax(ttmzm20A2Service.deleteTtmzm20A2ByIds(recIds));
    }

    /**
     * 确认排程
     */
    @Operation(summary = "确认排程")
    @Parameter(name = "recId", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/confirm/{recId}")
    public AjaxResult confirm(@PathVariable String recId) {
        return toAjax(ttmzm20A2Service.confirmSchedule(recId));
    }

    /**
     * 取消确认
     */
    @Operation(summary = "取消确认")
    @Parameter(name = "recId", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/cancel/{recId}")
    public AjaxResult cancel(@PathVariable String recId) {
        return toAjax(ttmzm20A2Service.cancelSchedule(recId));
    }

    /**
     * 完成排程
     */
    @Operation(summary = "完成排程")
    @Parameter(name = "recId", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/complete/{recId}")
    public AjaxResult complete(@PathVariable String recId) {
        return toAjax(ttmzm20A2Service.completeSchedule(recId));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
