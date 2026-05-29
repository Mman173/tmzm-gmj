package com.hg.tmzm.controller.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A1;
import com.hg.tmzm.service.device.ITtmzm2030A1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 定脱管机架管理 Controller
 */
@Tag(name = "定脱管机架管理")
@RestController
@RequestMapping("/tmzm/device/frame")
public class Ttmzm2030A1Controller extends BaseController {

    @Autowired
    private ITtmzm2030A1Service ttmzm2030A1Service;

    /**
     * 查询定脱管机架列表
     */
    @Operation(summary = "查询定脱管机架列表")
    @Parameter(name = "deviceType", description = "设备类型", in = ParameterIn.QUERY)
    @GetMapping("/list")
    public PageResult list(Ttmzm2030A1 ttmzm2030A1, PageQuery pageQuery) {
        IPage<Ttmzm2030A1> page = ttmzm2030A1Service.queryList(ttmzm2030A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 查询历史记录
     */
    @Operation(summary = "查询历史记录")
    @GetMapping("/history")
    public PageResult history(Ttmzm2030A1 ttmzm2030A1, PageQuery pageQuery) {
        IPage<Ttmzm2030A1> page = ttmzm2030A1Service.queryHistoryList(ttmzm2030A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取定脱管机架详细信息")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return success(ttmzm2030A1Service.queryById(id));
    }

    /**
     * 新增定脱管机架
     */
    @Operation(summary = "新增定脱管机架")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm2030A1 ttmzm2030A1) {
        return toAjax(ttmzm2030A1Service.insertTtmzm2030A1(ttmzm2030A1));
    }

    /**
     * 修改定脱管机架
     */
    @Operation(summary = "修改定脱管机架")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2030A1 ttmzm2030A1) {
        return toAjax(ttmzm2030A1Service.updateTtmzm2030A1(ttmzm2030A1));
    }

    /**
     * 报废定脱管机架
     */
    @Operation(summary = "报废定脱管机架")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/scrap/{id}")
    public AjaxResult scrap(@PathVariable String id) {
        return toAjax(ttmzm2030A1Service.scrapDevice(id, "system"));
    }

    /**
     * 变更去向
     */
    @Operation(summary = "变更去向")
    @PutMapping("/destination")
    public AjaxResult changeDestination(@RequestParam String id, @RequestParam String destination) {
        return toAjax(ttmzm2030A1Service.changeDestination(id, destination, "system"));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
