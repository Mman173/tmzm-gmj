package com.hg.tmzm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hg.tmzm.common.domain.PageQuery;
import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.PageResult;

/**
 * Controller基类
 */
public class BaseController {

    /**
     * 返回成功
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回成功消息
     */
    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * 返回失败消息
     */
    protected AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }

    /**
     * 返回分页数据
     */
    protected PageResult pageResult(IPage<?> page) {
        return new PageResult(page.getTotal(), page.getRecords());
    }
}
