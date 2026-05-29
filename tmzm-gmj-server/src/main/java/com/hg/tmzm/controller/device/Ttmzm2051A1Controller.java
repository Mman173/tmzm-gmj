package com.hg.tmzm.controller.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.punchroll.Ttmzm2051A1;
import com.hg.tmzm.service.device.ITtmzm2051A1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 穿孔辊管理 Controller
 */
@Tag(name = "穿孔辊管理")
@RestController
@RequestMapping("/tmzm/device/punchroll")
public class Ttmzm2051A1Controller extends BaseController {

    @Autowired
    private ITtmzm2051A1Service ttmzm2051A1Service;

    /**
     * 查询穿孔辊列表
     */
    @Operation(summary = "查询穿孔辊列表")
    @GetMapping("/list")
    public PageResult list(Ttmzm2051A1 ttmzm2051A1, PageQuery pageQuery) {
        IPage<Ttmzm2051A1> page = ttmzm2051A1Service.queryList(ttmzm2051A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取穿孔辊详细信息")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return success(ttmzm2051A1Service.queryById(id));
    }

    /**
     * 新增穿孔辊
     */
    @Operation(summary = "新增穿孔辊")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm2051A1 ttmzm2051A1) {
        return toAjax(ttmzm2051A1Service.insertTtmzm2051A1(ttmzm2051A1));
    }

    /**
     * 修改穿孔辊
     */
    @Operation(summary = "修改穿孔辊")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2051A1 ttmzm2051A1) {
        return toAjax(ttmzm2051A1Service.updateTtmzm2051A1(ttmzm2051A1));
    }

    /**
     * 报废穿孔辊
     */
    @Operation(summary = "报废穿孔辊")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/scrap/{id}")
    public AjaxResult scrap(@PathVariable String id) {
        return toAjax(ttmzm2051A1Service.scrapDevice(id, "system"));
    }

    /**
     * 上线穿孔辊
     */
    @Operation(summary = "上线穿孔辊")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/online/{id}")
    public AjaxResult online(@PathVariable String id) {
        return toAjax(ttmzm2051A1Service.onlineDevice(id, "system"));
    }

    /**
     * 下线穿孔辊
     */
    @Operation(summary = "下线穿孔辊")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/offline/{id}")
    public AjaxResult offline(@PathVariable String id) {
        return toAjax(ttmzm2051A1Service.offlineDevice(id, "system"));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
