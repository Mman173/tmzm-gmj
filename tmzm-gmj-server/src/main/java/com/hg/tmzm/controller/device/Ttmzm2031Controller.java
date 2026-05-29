package com.hg.tmzm.controller.device;

import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.frame.Ttmzm2031;
import com.hg.tmzm.service.device.ITtmzm2031Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定脱管机架装配信息 Controller
 */
@Tag(name = "定脱管机架装配信息")
@RestController
@RequestMapping("/tmzm/device/frame/assembly")
public class Ttmzm2031Controller extends BaseController {

    @Autowired
    private ITtmzm2031Service ttmzm2031Service;

    /**
     * 根据机架号查询装配信息
     */
    @Operation(summary = "根据机架号查询装配信息")
    @Parameter(name = "standNo", description = "机架号", required = true, in = ParameterIn.PATH)
    @GetMapping("/{standNo}")
    public AjaxResult getInfo(@PathVariable String standNo) {
        List<Ttmzm2031> list = ttmzm2031Service.queryByStandNo(standNo);
        return success(list);
    }

    /**
     * 修改装配参数
     */
    @Operation(summary = "修改装配参数")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2031 ttmzm2031) {
        return toAjax(ttmzm2031Service.updateAssembly(ttmzm2031));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
