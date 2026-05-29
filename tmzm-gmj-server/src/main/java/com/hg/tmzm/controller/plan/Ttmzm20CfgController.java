package com.hg.tmzm.controller.plan;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;
import com.hg.tmzm.controller.BaseController;
import com.hg.tmzm.entity.plan.Ttmzm20Cfg;
import com.hg.tmzm.service.plan.ITtmzm20CfgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 匹配配置 Controller
 */
@Tag(name = "匹配配置")
@RestController
@RequestMapping("/tmzm/plan/match")
public class Ttmzm20CfgController extends BaseController {

    @Autowired
    private ITtmzm20CfgService ttmzm20CfgService;

    /**
     * 查询匹配配置列表
     */
    @Operation(summary = "查询匹配配置列表")
    @GetMapping("/list")
    public PageResult list(Ttmzm20Cfg ttmzm20Cfg, PageQuery pageQuery) {
        IPage<Ttmzm20Cfg> page = ttmzm20CfgService.queryList(ttmzm20Cfg, pageQuery);
        return pageResult(page);
    }

    /**
     * 根据规格序号查询详细信息
     */
    @Operation(summary = "根据规格序号查询详细信息")
    @Parameter(name = "seqNo1", description = "规格序号", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{seqNo1}")
    public AjaxResult getDetail(@PathVariable String seqNo1) {
        List<Ttmzm20Cfg> list = ttmzm20CfgService.queryBySeqNo1(seqNo1);
        return success(list);
    }

    /**
     * 保存匹配配置（批量）
     */
    @Operation(summary = "保存匹配配置（批量）")
    @PostMapping("/save")
    public AjaxResult save(@RequestParam String seqNo1, @Validated @RequestBody List<Ttmzm20Cfg> list) {
        return toAjax(ttmzm20CfgService.saveBatch(seqNo1, list));
    }

    private AjaxResult toAjax(int result) {
        return result > 0 ? success() : error("操作失败");
    }
}
