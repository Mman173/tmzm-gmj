package com.hg.tmzm.entity.plan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hg.tmzm.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 生产规格配置
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM20A1")
public class Ttmzm20A1 extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String recId;

    @TableField("SIZING_ELONG_SIZE")
    private String sizingElongSize;

    @TableField("GROOVE_SIZE")
    private BigDecimal grooveSize;

    @TableField("PROD_MID_CNAME")
    private String prodMidCname;

    @TableField("PROD_MID_CODE")
    private String prodMidCode;

    @TableField("ROLL_DIA")
    private BigDecimal rollDia;

    @TableField("MANDREL_DIA")
    private BigDecimal mandrelDia;

    @TableField("MANDRIL_HEAD_DIA")
    private BigDecimal mandrilHeadDia;

    @TableField("MANDRIL_DIA")
    private BigDecimal mandrilDia;

    @TableField("SEQ_NO1")
    private String seqNo1;

    @TableField("ROLL_STATUS")
    private String rollStatus;

    @TableField("CONFIRM_TIME")
    private String confirmTime;

    @TableField("SPECIAL_REQ")
    private String specialReq;

    @TableField("TYPE")
    private String type;

    @TableField("ROLL_PLAN_REMARK")
    private String rollPlanRemark;

    @TableField("REMARK")
    private String remark;

    @TableField("DELETE_FLAG")
    private BigDecimal deleteFlag;

    @TableField("FIELD_C01")
    private String fieldC01;

    @TableField("FIELD_C02")
    private String fieldC02;

    @TableField("FIELD_C03")
    private String fieldC03;

    @TableField("FIELD_C04")
    private String fieldC04;

    @TableField("FIELD_C05")
    private String fieldC05;

    @TableField("FIELD_C06")
    private String fieldC06;

    @TableField("FIELD_C07")
    private String fieldC07;

    @TableField("FIELD_C08")
    private String fieldC08;

    @TableField("FIELD_C09")
    private String fieldC09;

    @TableField("FIELD_C10")
    private String fieldC10;
}
