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
 * 定脱管机架加工信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM2032")
public class Ttmzm2032 extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("DEV_NO")
    private String devNo;

    @TableField("STAND_NO")
    private String standNo;

    @TableField("MAC_TYPE")
    private String macType;

    @TableField("DEVICE_TYPE")
    private String deviceType;

    @TableField("DEV_CNAME")
    private String devCname;

    @TableField("WORK_SEQ_NO_TOOL")
    private String workSeqNoTool;

    @TableField("DU_FLAG")
    private String duFlag;

    @TableField("LAST_GROOVE_FORM_NO")
    private String lastGrooveFormNo;

    @TableField("PROC_GROOVE_FORM_NO")
    private String procGrooveFormNo;

    @TableField("CHILD_DEV_NO1")
    private String childDevNo1;

    @TableField("CHILD_DEV_NO2")
    private String childDevNo2;

    @TableField("CHILD_DEV_NO3")
    private String childDevNo3;

    @TableField("ROLLER_NO1")
    private String rollerNo1;

    @TableField("ROLLER_NO2")
    private String rollerNo2;

    @TableField("ROLLER_NO3")
    private String rollerNo3;

    @TableField("FACT_2AI")
    private BigDecimal fact2Ai;

    @TableField("FACT_2BI")
    private BigDecimal fact2Bi;

    @TableField("PROC_2AI")
    private BigDecimal proc2Ai;

    @TableField("PROC_2BI")
    private BigDecimal proc2Bi;

    @TableField("FACT_ROLL_DIA1")
    private BigDecimal factRollDia1;

    @TableField("FACT_ROLL_DIA2")
    private BigDecimal factRollDia2;

    @TableField("FACT_ROLL_DIA3")
    private BigDecimal factRollDia3;

    @TableField("PROC_ROLLER_DIA1")
    private BigDecimal procRollerDia1;

    @TableField("PROC_ROLLER_DIA2")
    private BigDecimal procRollerDia2;

    @TableField("PROC_ROLLER_DIA3")
    private BigDecimal procRollerDia3;

    @TableField("HOURS")
    private BigDecimal hours;

    @TableField("PROC_MAKER")
    private String procMaker;

    @TableField("PRC_TIME")
    private String prcTime;

    @TableField("PROC_SHIFT")
    private String procShift;

    @TableField("PROC_GRP")
    private String procGrp;

    @TableField("PROC_REMARK")
    private String procRemark;

    @TableField("CK_GROOVE_FORM_NO")
    private String ckGrooveFormNo;

    @TableField("CK_2AI")
    private BigDecimal ck2Ai;

    @TableField("CK_2BI")
    private BigDecimal ck2Bi;

    @TableField("CK_ROLLER_DIA1")
    private BigDecimal ckRollerDia1;

    @TableField("CK_ROLLER_DIA2")
    private BigDecimal ckRollerDia2;

    @TableField("CK_ROLLER_DIA3")
    private BigDecimal ckRollerDia3;

    @TableField("CK_HOURS")
    private BigDecimal ckHours;

    @TableField("CK_MAKER")
    private String ckMaker;

    @TableField("CK_TIME")
    private String ckTime;

    @TableField("CK_SHIFT")
    private String ckShift;

    @TableField("CK_GRP")
    private String ckGrp;

    @TableField("CK_REMARK")
    private String ckRemark;

    @TableField("OK_FLAG")
    private String okFlag;

    @TableField("PROC_GROOVE_FORM_NO_LIST")
    private String procGrooveFormNoList;

    @TableField("LAST_GROOVE_FORM_NO_LIST")
    private String lastGrooveFormNoList;

    @TableField("FACT_LONG_AXLE_DIA")
    private BigDecimal factLongAxleDia;

    @TableField("FACT_SHORT_AXLE_DIA")
    private BigDecimal factShortAxleDia;

    @TableField("FACT_WA")
    private BigDecimal factWa;

    @TableField("WA")
    private BigDecimal wa;

    @TableField("WD")
    private BigDecimal wd;

    @TableField("LONG_AXLE_DIA")
    private BigDecimal longAxleDia;

    @TableField("SHORT_AXLE_DIA")
    private BigDecimal shortAxleDia;

    @TableField("ZC_WORSE_III")
    private BigDecimal zcWorseIii;

    @TableField("ZC_WORSE_II_III")
    private BigDecimal zcWorseIiIii;

    @TableField("ZC_WORSE_III_I")
    private BigDecimal zcWorseIiiI;

    @TableField("FACTORY_DIV")
    private String factoryDiv;

    @TableField("ROLLER_DIA1")
    private BigDecimal rollerDia1;

    @TableField("ROLLER_DIA2")
    private BigDecimal rollerDia2;

    @TableField("ROLLER_DIA3")
    private BigDecimal rollerDia3;

    @TableField("ROLLER_DIA_PJ")
    private BigDecimal rollerDiaPj;

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
