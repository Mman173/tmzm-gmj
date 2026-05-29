package com.hg.tmzm.entity.device.punchroll;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hg.tmzm.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 穿孔辊组成信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM2051A2")
public class Ttmzm2051A2 extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("ROTATING_DRUM_NO")
    private String rotatingDrumNo;

    @TableField("ROLLER_NO_TR")
    private String rollerNoTr;

    @TableField("ORIGIN_ASMAT_NO")
    private String originAsmatNo;

    @TableField("ASMAT_NO")
    private String asmatNo;

    @TableField("ASMAT_NAME")
    private String asmatName;

    @TableField("STEEL_GRADE_BRAND")
    private String steelGradeBrand;

    @TableField("PROJECT_NO")
    private String projectNo;

    @TableField("CONTRACT_SERIAL")
    private String contractSerial;

    @TableField("SPEC")
    private String spec;

    @TableField("TYPE_SPEC")
    private String typeSpec;

    @TableField("PIC_NO")
    private String picNo;

    @TableField("TEXTURE")
    private String texture;

    @TableField("BRAND")
    private String brand;

    @TableField("OUT_FACTORY_ID")
    private String outFactoryId;

    @TableField("USE")
    private String use;

    @TableField("UNIT")
    private String unit;

    @TableField("QTY")
    private BigDecimal qty;

    @TableField("MAT_STATUS")
    private String matStatus;

    @TableField("TYPE")
    private String type;

    @TableField("TYPE_CODE")
    private String typeCode;

    @TableField("SERIAL_NO")
    private String serialNo;

    @TableField("FJ_TYPE")
    private String fjType;

    @TableField("TYPE_NAME")
    private String typeName;

    @TableField("BRANCHES_NUMBER")
    private BigDecimal branchesNumber;

    @TableField("STANDARD_NUMBER")
    private BigDecimal standardNumber;

    @TableField("TOTAL_NUMBER")
    private BigDecimal totalNumber;

    @TableField("CREATE_TIME01")
    private String createTime01;

    @TableField("SUPPLIER_NAME")
    private String supplierName;

    @TableField("STAND_ID")
    private String standId;

    @TableField("FRAME_TYPE")
    private String frameType;

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
