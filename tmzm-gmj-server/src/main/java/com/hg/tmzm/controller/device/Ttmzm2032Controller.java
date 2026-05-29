package com.hg.tmzm.controller.device;

import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.frame.Ttmzm2032;
import com.hg.tmzm.service.device.ITtmzm2032Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定脱管机架加工信息 Controller
 */
@Tag(name = "定脱管机架加工信息")
@RestController
@RequestMapping("/tmzm/device/frame/processing")
public class Ttmzm2032Controller extends BaseController {

    @Autowired
    private ITtmzm2032Service ttmzm2032Service;

    /**
     * 根据机架号查询加工信息列表
     */
    @Operation(summary = "根据机架号查询加工信息列表")
    @Parameter(name = "standNo", description = "机架号", required = true, in = ParameterIn.PATH)
    @GetMapping("/list/{standNo}")
    public AjaxResult list(@PathVariable String standNo) {
        List<Ttmzm2032> list = ttmzm2032Service.queryByStandNo(standNo);
        return success(list);
    }

    /**
     * 修改加工参数
     */
    @Operation(summary = "修改加工参数")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2032 ttmzm2032) {
        return toAjax(ttmzm2032Service.updateProcessing(ttmzm2032));
    }

    /**
     * 确认加工
     */
    @Operation(summary = "确认加工")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/confirm/{id}")
    public AjaxResult confirm(@PathVariable String id) {
        return toAjax(ttmzm2032Service.confirmProcessing(id, "system"));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
