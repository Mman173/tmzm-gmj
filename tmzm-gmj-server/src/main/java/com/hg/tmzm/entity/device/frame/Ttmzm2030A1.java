package com.hg.tmzm.entity.device.frame;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hg.tmzm.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 定脱管机架主信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM2030A1")
public class Ttmzm2030A1 extends BaseEntity {

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

    @TableField("PRE_STAND_NO")
    private String preStandNo;

    @TableField("ASMAT_NO")
    private String asmatNo;

    @TableField("ASMAT_NAME")
    private String asmatName;

    @TableField("SPEC")
    private String spec;

    @TableField("TYPE_SPEC")
    private String typeSpec;

    @TableField("BRANCHES_NUMBER")
    private BigDecimal branchesNumber;

    @TableField("STANDARD_NUMBER")
    private BigDecimal standardNumber;

    @TableField("TOTAL_NUMBER")
    private BigDecimal totalNumber;

    @TableField("DEVICE_STATUS")
    private String deviceStatus;

    @TableField("DESTINATION_STATION")
    private String destinationStation;

    @TableField("LAST_DESTINATION_STATION")
    private String lastDestinationStation;

    @TableField("STATUS")
    private String status;

    @TableField("TYPE")
    private String type;

    @TableField("DEVICE_TYPE")
    private String deviceType;

    @TableField("REMARK")
    private String remark;

    @TableField("SUPPLIER_NAME")
    private String supplierName;

    @TableField("ITEM_CODE2")
    private String itemCode2;

    @TableField("ASMAT_TYPE")
    private String asmatType;

    @TableField("ASMAT_TYPE_DESC")
    private String asmatTypeDesc;

    @TableField("ORIGIN_ASMAT_NO")
    private String originAsmatNo;

    @TableField("PIC_NO")
    private String picNo;

    @TableField("TEXTURE")
    private String texture;

    @TableField("BRAND")
    private String brand;

    @TableField("OUT_FACTORY_ID")
    private String outFactoryId;

    @TableField("VENDER_NAME")
    private String venderName;

    @TableField("USE")
    private String use;

    @TableField("QTY")
    private BigDecimal qty;

    @TableField("DEVICE_ID")
    private String deviceId;

    @TableField("RETURN_NUMBER")
    private BigDecimal returnNumber;

    @TableField("CONFIGURE_ID")
    private String configureId;

    @TableField("LOCATION")
    private String location;

    @TableField("DEVICE_FLAG")
    private String deviceFlag;

    @TableField("SERIAL_NO")
    private String serialNo;

    @TableField("PLAN_NO")
    private String planNo;

    @TableField("PRE_PLAN_NO")
    private String prePlanNo;

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
