package com.hg.tmzm.controller.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.drum.Ttmzm2050A1;
import com.hg.tmzm.service.device.ITtmzm2050A1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 转鼓管理 Controller
 */
@Tag(name = "转鼓管理")
@RestController
@RequestMapping("/tmzm/device/drum")
public class Ttmzm2050A1Controller extends BaseController {

    @Autowired
    private ITtmzm2050A1Service ttmzm2050A1Service;

    /**
     * 查询转鼓列表
     */
    @Operation(summary = "查询转鼓列表")
    @GetMapping("/list")
    public PageResult list(Ttmzm2050A1 ttmzm2050A1, PageQuery pageQuery) {
        IPage<Ttmzm2050A1> page = ttmzm2050A1Service.queryList(ttmzm2050A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取转鼓详细信息")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return success(ttmzm2050A1Service.queryById(id));
    }

    /**
     * 新增转鼓
     */
    @Operation(summary = "新增转鼓")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm2050A1 ttmzm2050A1) {
        return toAjax(ttmzm2050A1Service.insertTtmzm2050A1(ttmzm2050A1));
    }

    /**
     * 修改转鼓
     */
    @Operation(summary = "修改转鼓")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2050A1 ttmzm2050A1) {
        return toAjax(ttmzm2050A1Service.updateTtmzm2050A1(ttmzm2050A1));
    }

    /**
     * 报废转鼓
     */
    @Operation(summary = "报废转鼓")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/scrap/{id}")
    public AjaxResult scrap(@PathVariable String id) {
        return toAjax(ttmzm2050A1Service.scrapDevice(id, "system"));
    }

    /**
     * 上线转鼓
     */
    @Operation(summary = "上线转鼓")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/online/{id}")
    public AjaxResult online(@PathVariable String id) {
        return toAjax(ttmzm2050A1Service.onlineDevice(id, "system"));
    }

    /**
     * 下线转鼓
     */
    @Operation(summary = "下线转鼓")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/offline/{id}")
    public AjaxResult offline(@PathVariable String id) {
        return toAjax(ttmzm2050A1Service.offlineDevice(id, "system"));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
