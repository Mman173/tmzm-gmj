package com.hg.tmzm.entity.device.drum;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hg.tmzm.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 穿孔辊加工
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM2052")
public class Ttmzm2052 extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("STEEL_GRADE_BRAND")
    private String steelGradeBrand;

    @TableField("DEVICE_TYPE")
    private String deviceType;

    @TableField("NOMINAL_DIA")
    private BigDecimal nominalDia;

    @TableField("LAST")
    private BigDecimal last;

    @TableField("PRE")
    private BigDecimal pre;

    @TableField("SHIFT_NO")
    private String shiftNo;

    @TableField("GROUP_NUM")
    private String groupNum;

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
