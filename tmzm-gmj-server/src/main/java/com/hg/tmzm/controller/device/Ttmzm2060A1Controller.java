package com.hg.tmzm.controller.device;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.device.rolling.Ttmzm2060A1;
import com.hg.tmzm.service.device.ITtmzm2060A1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 连轧机架/辊管理 Controller
 */
@Tag(name = "连轧机架/辊管理")
@RestController
@RequestMapping("/tmzm/device/rolling")
public class Ttmzm2060A1Controller extends BaseController {

    @Autowired
    private ITtmzm2060A1Service ttmzm2060A1Service;

    /**
     * 查询连轧机架/辊列表
     */
    @Operation(summary = "查询连轧机架/辊列表")
    @Parameter(name = "deviceType", description = "设备类型", in = ParameterIn.QUERY)
    @GetMapping("/list")
    public PageResult list(Ttmzm2060A1 ttmzm2060A1, PageQuery pageQuery) {
        IPage<Ttmzm2060A1> page = ttmzm2060A1Service.queryList(ttmzm2060A1, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据编号获取详细信息
     */
    @Operation(summary = "获取连轧机架/辊详细信息")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return success(ttmzm2060A1Service.queryById(id));
    }

    /**
     * 新增连轧机架/辊
     */
    @Operation(summary = "新增连轧机架/辊")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Ttmzm2060A1 ttmzm2060A1) {
        return toAjax(ttmzm2060A1Service.insertTtmzm2060A1(ttmzm2060A1));
    }

    /**
     * 修改连轧机架/辊
     */
    @Operation(summary = "修改连轧机架/辊")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Ttmzm2060A1 ttmzm2060A1) {
        return toAjax(ttmzm2060A1Service.updateTtmzm2060A1(ttmzm2060A1));
    }

    /**
     * 报废连轧机架/辊
     */
    @Operation(summary = "报废连轧机架/辊")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/scrap/{id}")
    public AjaxResult scrap(@PathVariable String id) {
        return toAjax(ttmzm2060A1Service.scrapDevice(id, "system"));
    }

    /**
     * 下线连轧机架/辊
     */
    @Operation(summary = "下线连轧机架/辊")
    @Parameter(name = "id", description = "记录ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/offline/{id}")
    public AjaxResult offline(@PathVariable String id) {
        return toAjax(ttmzm2060A1Service.offlineDevice(id, "system"));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
