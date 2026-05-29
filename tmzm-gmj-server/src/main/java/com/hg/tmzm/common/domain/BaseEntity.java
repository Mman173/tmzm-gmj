package com.hg.tmzm.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base entity with common audit fields matching the C++ header conventions
 *
 * @author hg
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Record create time */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime recCreateTime;

    /** Record creator */
    @TableField(fill = FieldFill.INSERT)
    private String recCreator;

    /** Record revise (last update) time */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime recReviseTime;

    /** Record revisor (last updater) */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String recRevisor;

    /** Record erase (delete) time */
    private LocalDateTime recEraseTime;

    /** Record erasor (deleter) */
    private String recErasor;

    /** Archive flag (logical delete marker): 0=active, 1=archived/deleted */
    @TableLogic
    private String archiveFlag;

    /** Archive stamp number */
    private String archiveStampNo;

    /** Company code */
    private String companyCode;

    /** Company name */
    private String companyName;
}
