package com.hg.tmzm.controller.device;

import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.drum.Ttmzm2050A2;
import com.hg.tmzm.service.device.ITtmzm2050A2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 转鼓组成信息 Controller
 */
@Tag(name = "转鼓组成信息")
@RestController
@RequestMapping("/tmzm/device/drum/detail")
public class Ttmzm2050A2Controller extends BaseController {

    @Autowired
    private ITtmzm2050A2Service ttmzm2050A2Service;

    /**
     * 根据转鼓号查询组成信息列表
     */
    @Operation(summary = "根据转鼓号查询组成信息列表")
    @Parameter(name = "rotatingDrumNo", description = "转鼓号", required = true, in = ParameterIn.PATH)
    @GetMapping("/list/{rotatingDrumNo}")
    public AjaxResult list(@PathVariable String rotatingDrumNo) {
        List<Ttmzm2050A2> list = ttmzm2050A2Service.queryByRotatingDrumNo(rotatingDrumNo);
        return success(list);
    }

    /**
     * 保存组成信息（批量）
     */
    @Operation(summary = "保存组成信息（批量）")
    @PostMapping("/save")
    public AjaxResult save(@RequestParam String rotatingDrumNo, @Validated @RequestBody List<Ttmzm2050A2> list) {
        return toAjax(ttmzm2050A2Service.saveBatch(rotatingDrumNo, list));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
