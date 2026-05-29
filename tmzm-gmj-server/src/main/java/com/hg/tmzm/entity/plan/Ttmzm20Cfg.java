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
 * 匹配配置
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM20CFG")
public class Ttmzm20Cfg extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String seqNo1;

    @TableField("SEQ_NO")
    private BigDecimal seqNo;

    @TableField("ID")
    private String id;

    @TableField("FRAME_TYPE")
    private String frameType;

    @TableField("STAND_NO")
    private String standNo;

    @TableField("ASMAT_NO")
    private String asmatNo;

    @TableField("ASMAT_NAME")
    private String asmatName;

    @TableField("PROC_GROOVE_FORM_NO")
    private String procGrooveFormNo;

    @TableField("ROTATING_DRUM_NO")
    private String rotatingDrumNo;

    @TableField("ROTATING_DRUM_TYPE")
    private String rotatingDrumType;

    @TableField("ROLLER_NO_TR")
    private String rollerNoTr;

    @TableField("BRANCHES_NUMBER")
    private BigDecimal branchesNumber;

    @TableField("STANDARD_NUMBER")
    private BigDecimal standardNumber;

    @TableField("TOTAL_NUMBER")
    private BigDecimal totalNumber;

    @TableField("DESTINATION_STATION")
    private String destinationStation;

    @TableField("DEVICE_TYPE")
    private String deviceType;

    @TableField("DEVICE_STATUS")
    private String deviceStatus;

    @TableField("STATUS")
    private String status;

    @TableField("TYPE")
    private String type;

    @TableField("REMARK")
    private String remark;

    @TableField("SUPPLIER_NAME")
    private String supplierName;

    @TableField("OFFLINE_TIME")
    private String offlineTime;

    @TableField("ONLINE_TIME")
    private String onlineTime;

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
