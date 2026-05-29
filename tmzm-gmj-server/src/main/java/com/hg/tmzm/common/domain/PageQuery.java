package com.hg.tmzm.common.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * Pagination query parameters
 *
 * @author hg
 */
@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Current page number, default 1 */
    private Integer pageNum = 1;

    /** Page size, default 10 */
    private Integer pageSize = 10;

    /**
     * Build a MyBatis-Plus Page object from current parameters
     *
     * @return Page object for pagination query
     */
    public <T> Page<T> buildPage() {
        return new Page<>(pageNum, pageSize);
    }

    /**
     * Build a MyBatis-Plus Page object with custom type
     *
     * @param pageNum  page number
     * @param pageSize page size
     * @return Page object for pagination query
     */
    public <T> Page<T> buildPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            pageNum = this.pageNum;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = this.pageSize;
        }
        return new Page<>(pageNum, pageSize);
    }
}
