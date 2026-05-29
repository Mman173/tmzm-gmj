package com.hg.tmzm.controller.device;

import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.frame.Ttmzm2030A2;
import com.hg.tmzm.service.device.ITtmzm2030A2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定脱管机架组成信息 Controller
 */
@Tag(name = "定脱管机架组成信息")
@RestController
@RequestMapping("/tmzm/device/frame/detail")
public class Ttmzm2030A2Controller extends BaseController {

    @Autowired
    private ITtmzm2030A2Service ttmzm2030A2Service;

    /**
     * 根据机架号查询组成信息列表
     */
    @Operation(summary = "根据机架号查询组成信息列表")
    @Parameter(name = "standNo", description = "机架号", required = true, in = ParameterIn.PATH)
    @GetMapping("/list/{standNo}")
    public AjaxResult list(@PathVariable String standNo) {
        List<Ttmzm2030A2> list = ttmzm2030A2Service.queryByStandNo(standNo);
        return success(list);
    }

    /**
     * 保存组成信息（批量）
     */
    @Operation(summary = "保存组成信息（批量）")
    @PostMapping("/save")
    public AjaxResult save(@RequestParam String standNo, @Validated @RequestBody List<Ttmzm2030A2> list) {
        return toAjax(ttmzm2030A2Service.saveBatch(standNo, list));
    }

    /**
     * 清空物料字段
     */
    @Operation(summary = "清空物料字段")
    @PutMapping("/clear")
    public AjaxResult clear(@RequestParam String standNo) {
        return toAjax(ttmzm2030A2Service.clearMaterial(standNo, ""));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
