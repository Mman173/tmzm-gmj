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
 * 定脱管机架装配信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM2031")
public class Ttmzm2031 extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("STAND_NO")
    private String standNo;

    @TableField("MAC_TYPE")
    private String macType;

    @TableField("NEW_ROLL_NO")
    private String newRollNo;

    @TableField("C_WITH_A_ROLL_BITE")
    private String cWithARollBite;

    @TableField("A_WITH_B_ROLL_BITE")
    private String aWithBRollBite;

    @TableField("B_WITH_C_ROLL_BITE")
    private String bWithCRollBite;

    @TableField("HORIZONTAL_CENTER")
    private String horizontalCenter;

    @TableField("RACK_HEIGHT")
    private String rackHeight;

    @TableField("RACK_WIDTH")
    private String rackWidth;

    @TableField("INSTALL_MAKER")
    private String installMaker;

    @TableField("ASSEMBLY_TIME")
    private String assemblyTime;

    @TableField("INSTALL_SHIFT")
    private String installShift;

    @TableField("INSTALL_GRP")
    private String installGrp;

    @TableField("INSTALL_REMARK")
    private String installRemark;

    @TableField("RACK_SLEEVE_GEAR")
    private String rackSleeveGear;

    @TableField("LOCATED_BLOCK_THICKNESS")
    private String locatedBlockThickness;

    @TableField("COOLING_WATER")
    private String coolingWater;

    @TableField("NO_LEAKAGE_GREASE")
    private String noLeakageGrease;

    @TableField("TURN_IS_ABNORMAL")
    private String turnIsAbnormal;

    @TableField("BEARING_STATE")
    private String bearingState;

    // SPG_A group
    @TableField("SPG_A_QDZC")
    private String spgAQdzc;

    @TableField("SPG_A_WDZC")
    private String spgAWdzc;

    @TableField("SPG_A_ZGCJ")
    private String spgAZgcj;

    @TableField("SPG_A_ZGNJ")
    private String spgAZgnj;

    @TableField("SPG_A_ZGYD")
    private String spgAZgyd;

    @TableField("SPG_A_ZYZCCC")
    private String spgAZyzccc;

    @TableField("SPG_A_ZYZCCC1")
    private String spgAZyzccc1;

    @TableField("SPG_A_ZYZCCC2")
    private String spgAZyzccc2;

    // CZG_B group
    @TableField("CZG_B_QDZC")
    private String czgBQdzc;

    @TableField("CZG_B_WDZC")
    private String czgBWdzc;

    @TableField("CZG_B_ZGCJ")
    private String czgBZgcj;

    @TableField("CZG_B_ZGNJ")
    private String czgBZgnj;

    @TableField("CZG_B_ZGYD")
    private String czgBZgyd;

    @TableField("CZG_B_ZYZCCC")
    private String czgBZyzccc;

    @TableField("CZG_B_ZYZCCC1")
    private String czgBZyzccc1;

    @TableField("CZG_B_ZYZCCC2")
    private String czgBZyzccc2;

    // DZG_C group
    @TableField("DZG_C_QDZC")
    private String dzgCQdzc;

    @TableField("DZG_C_WDZC")
    private String dzgCWdzc;

    @TableField("DZG_C_ZGCJ")
    private String dzgCZgcj;

    @TableField("DZG_C_ZGNJ")
    private String dzgCZgnj;

    @TableField("DZG_C_ZGYD")
    private String dzgCZgyd;

    @TableField("DZG_C_ZYZCCC")
    private String dzgCZyzccc;

    @TableField("DZG_C_ZYZCCC1")
    private String dzgCZyzccc1;

    @TableField("DZG_C_ZYZCCC2")
    private String dzgCZyzccc2;

    @TableField("JJLG")
    private String jjlg;

    // SPG_A detail
    @TableField("SPG_A_GH")
    private String spgAGh;

    @TableField("SPG_A_MC")
    private String spgAMc;

    @TableField("SPG_A_QDZC_GH")
    private String spgAQdzcGh;

    @TableField("SPG_A_QDZC_MC")
    private String spgAQdzcMc;

    @TableField("SPG_A_WDZC_GH")
    private String spgAWdzcGh;

    @TableField("SPG_A_WDZC_MC")
    private String spgAWdzcMc;

    // CZG_B detail
    @TableField("CZG_B_GH")
    private String czgBGh;

    @TableField("CZG_B_MC")
    private String czgBMc;

    @TableField("CZG_B_QDZC_GH")
    private String czgBQdzcGh;

    @TableField("CZG_B_QDZC_MC")
    private String czgBQdzcMc;

    @TableField("CZG_B_WDZC_GH")
    private String czgBWdzcGh;

    @TableField("CZG_B_WDZC_MC")
    private String czgBWdzcMc;

    // DZG_C detail
    @TableField("DZG_C_GH")
    private String dzgCGh;

    @TableField("DZG_C_MC")
    private String dzgCMc;

    @TableField("DZG_C_QDZC_GH")
    private String dzgCQdzcGh;

    @TableField("DZG_C_QDZC_MC")
    private String dzgCQdzcMc;

    @TableField("DZG_C_WDZC_GH")
    private String dzgCWdzcGh;

    @TableField("DZG_C_WDZC_MC")
    private String dzgCWdzcMc;

    @TableField("IS_YZ")
    private String isYz;

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
