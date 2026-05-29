package com.hg.tmzm.common.response;

import java.io.Serializable;
import java.util.List;

/**
 * Pagination result wrapper
 *
 * @author hg
 */
public class PageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Total record count */
    private long total;

    /** Data rows */
    private List<?> rows;

    /** Status code */
    private int code;

    /** Message */
    private String msg;

    public PageResult() {
    }

    public PageResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
        this.code = 200;
        this.msg = "操作成功";
    }

    public PageResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Create a successful page result
     */
    public static PageResult success(long total, List<?> rows) {
        return new PageResult(total, rows);
    }

    /**
     * Create an error page result
     */
    public static PageResult error(String msg) {
        return new PageResult(500, msg);
    }

    /**
     * Create an error page result with code
     */
    public static PageResult error(int code, String msg) {
        return new PageResult(code, msg);
    }

    @Override
    public String toString() {
        return "PageResult{total=" + total + ", rows count=" + (rows != null ? rows.size() : 0)
                + ", code=" + code + ", msg='" + msg + "'}";
    }
}
