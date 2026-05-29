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
 * 排程合同对应
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("TTMZM20A2O")
public class Ttmzm20A2o extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private String seqNo1;

    @TableField("PLAN_BACKLOG_CODE")
    private String planBacklogCode;

    @TableField("PLAN_STATUS")
    private String planStatus;

    @TableField("PLAN_TYPE")
    private String planType;

    @TableField("PLAN_NO")
    private String planNo;

    @TableField("PLAN_SEQ_NO")
    private BigDecimal planSeqNo;

    @TableField("PLAN_SEQ_NO_DAY")
    private BigDecimal planSeqNoDay;

    @TableField("PLAN_DATE_FROM")
    private String planDateFrom;

    @TableField("PLAN_DATE_TO")
    private String planDateTo;

    @TableField("RL_NO")
    private String rlNo;

    @TableField("RL_ITEM_NO")
    private String rlItemNo;

    @TableField("ROLL_DIA")
    private BigDecimal rollDia;

    @TableField("ROLL_WAL_THICK")
    private BigDecimal rollWalThick;

    @TableField("REQR_ROLL_TUBE")
    private BigDecimal reqrRollTube;

    @TableField("REQR_ROLL_WT")
    private BigDecimal reqrRollWt;

    @TableField("PROD_MID_CODE")
    private String prodMidCode;

    @TableField("PROD_MID_CNAME")
    private String prodMidCname;

    @TableField("MTRL_NO")
    private String mtrlNo;

    @TableField("MTRL_TEXT")
    private String mtrlText;

    @TableField("BILLET_DIA")
    private BigDecimal billetDia;

    @TableField("GROOVE_CODE")
    private String grooveCode;

    @TableField("GROOVE_SIZE")
    private BigDecimal grooveSize;

    @TableField("GROOVE_DESC")
    private String grooveDesc;

    @TableField("SUB_GROOVE_CODE")
    private String subGrooveCode;

    @TableField("SUB_GROOVE_DESC")
    private String subGrooveDesc;

    @TableField("SUB_GROOVE_GROUP_CODE")
    private String subGrooveGroupCode;

    @TableField("SUB_GROOVE_TYPE_TEXT")
    private String subGrooveTypeText;

    @TableField("BILLET_LEN")
    private BigDecimal billetLen;

    @TableField("BILLET_WT")
    private BigDecimal billetWt;

    @TableField("RAW_LEN")
    private BigDecimal rawLen;

    @TableField("MAT_NO")
    private String matNo;

    @TableField("MAT_KIND")
    private String matKind;

    @TableField("PONO")
    private String pono;

    @TableField("HEAT_NO")
    private String heatNo;

    @TableField("CZ_RL_NO")
    private String czRlNo;

    @TableField("ORIGIN_HEAT_NO")
    private String originHeatNo;

    @TableField("PLAN_TUBE")
    private BigDecimal planTube;

    @TableField("PLAN_WT")
    private BigDecimal planWt;

    @TableField("DEVO_TUBE")
    private BigDecimal devoTube;

    @TableField("DEVO_WT")
    private BigDecimal devoWt;

    @TableField("MAT_TUBE")
    private BigDecimal matTube;

    @TableField("MAT_THEORY_WT")
    private BigDecimal matTheoryWt;

    @TableField("MAT_ACT_WT")
    private BigDecimal matActWt;

    @TableField("FIX_FLAG")
    private String fixFlag;

    @TableField("LEN_FROM")
    private BigDecimal lenFrom;

    @TableField("LEN_TO")
    private BigDecimal lenTo;

    @TableField("STOCK_PLACE_NO")
    private String stockPlaceNo;

    @TableField("SAW_CUT")
    private BigDecimal sawCut;

    @TableField("CUT_LEN")
    private BigDecimal cutLen;

    @TableField("CUT_WT")
    private BigDecimal cutWt;

    @TableField("CUT_BILLET")
    private BigDecimal cutBillet;

    @TableField("REMAIN_LEN")
    private BigDecimal remainLen;

    @TableField("REMAIN_BILLET")
    private BigDecimal remainBillet;

    @TableField("REMAIN_WT")
    private BigDecimal remainWt;

    @TableField("START_PROD_TIME")
    private String startProdTime;

    @TableField("END_PROD_TIME")
    private String endProdTime;

    @TableField("PLAN_PROD_TIME")
    private BigDecimal planProdTime;

    @TableField("PLAN_SEND_TIME")
    private String planSendTime;

    @TableField("PLAN_SEND_FLAG")
    private String planSendFlag;

    @TableField("PLAN_MAKER")
    private String planMaker;

    @TableField("REMARK_PS")
    private String remarkPs;

    @TableField("UNRULL_FLAG")
    private String unrullFlag;

    @TableField("PLAN_TENDAY_FLAG")
    private String planTendayFlag;

    @TableField("SPVISE_FLAG")
    private String spviseFlag;

    @TableField("COME_PROC_FLAG")
    private String comeProcFlag;

    @TableField("BILLET_TAIL_FLAG")
    private String billetTailFlag;

    @TableField("BILLET_USAGE_FLAG")
    private String billetUsageFlag;

    @TableField("STEEL_MAKING_METHOD_CODE")
    private String steelMakingMethodCode;

    @TableField("STEEL_MAKING_METHOD")
    private String steelMakingMethod;

    @TableField("BILLET_PROC_MODE_CODE")
    private String billetProcModeCode;

    @TableField("BILLET_PROC_MODE")
    private String billetProcMode;

    @TableField("BILLET_STRIP_CODE")
    private String billetStripCode;

    @TableField("BILLET_STRIP")
    private String billetStrip;

    @TableField("BILLET_DIF_CODE")
    private String billetDifCode;

    @TableField("BILLET_DIA_STRIP")
    private BigDecimal billetDiaStrip;

    @TableField("MAT_APP_NO")
    private String matAppNo;

    @TableField("PLAN_EXEC_SEQ_NO")
    private BigDecimal planExecSeqNo;

    @TableField("REMARK")
    private String remark;

    @TableField("FACTORY_DIV")
    private String factoryDiv;

    @TableField("HOT_PATH_CODE")
    private String hotPathCode;

    @TableField("ORDER_NO")
    private String orderNo;

    @TableField("SG_CODE")
    private String sgCode;

    @TableField("SG_SIGN")
    private String sgSign;

    @TableField("ORD_SPEC1")
    private BigDecimal ordSpec1;

    @TableField("ORD_SPEC2")
    private BigDecimal ordSpec2;

    @TableField("ORD_SPEC3")
    private BigDecimal ordSpec3;

    @TableField("ORD_SPEC4")
    private BigDecimal ordSpec4;

    @TableField("ORD_SPEC5")
    private BigDecimal ordSpec5;

    @TableField("ORD_NUM")
    private BigDecimal ordNum;

    @TableField("SG_STD")
    private String sgStd;

    @TableField("SPECIAL_REQ")
    private String specialReq;

    @TableField("MANDRIL_HEAD_DIA")
    private BigDecimal mandrilHeadDia;

    @TableField("MANDRIL_DIA")
    private BigDecimal mandrilDia;

    @TableField("SAW_LEN")
    private BigDecimal sawLen;

    @TableField("MULTIPLE_NUM")
    private BigDecimal multipleNum;

    @TableField("DIM_TOL_STANDARD")
    private String dimTolStandard;

    @TableField("STEP_FURNACE")
    private String stepFurnace;

    @TableField("FLAW_DETECTION")
    private String flawDetection;

    @TableField("THE_SUCC_RATE")
    private String theSuccRate;

    @TableField("MANDREL_DIA")
    private BigDecimal mandrelDia;

    @TableField("WAL_THICK_PI")
    private BigDecimal walThickPi;

    @TableField("WHOLE_BACKLOG_CODE")
    private String wholeBacklogCode;

    @TableField("BILLET_TUBE")
    private BigDecimal billetTube;

    @TableField("DEVO_RATE")
    private BigDecimal devoRate;

    @TableField("ORDER_LEN_MIN")
    private BigDecimal orderLenMin;

    @TableField("ORDER_LEN_MAX")
    private BigDecimal orderLenMax;

    @TableField("HOT_MULTIPLE_NUM")
    private BigDecimal hotMultipleNum;

    @TableField("HOT_MULTIPLE_LEN")
    private BigDecimal hotMultipleLen;

    @TableField("SEQ_NO")
    private BigDecimal seqNo;

    @TableField("PIERC_DIA")
    private BigDecimal piercDia;

    @TableField("PIERC_WAL_THICK")
    private BigDecimal piercWalThick;

    @TableField("SRM_DIA")
    private BigDecimal srmDia;

    @TableField("SRM_WAL_THICK_MIN")
    private BigDecimal srmWalThickMin;

    @TableField("TOTAL_LEN")
    private BigDecimal totalLen;

    @TableField("VALID_FLAG")
    private String validFlag;

    @TableField("FULL_FLAG")
    private String fullFlag;

    @TableField("SEQ_ID")
    private BigDecimal seqId;

    @TableField("SIZING_ELONG_SIZE")
    private String sizingElongSize;

    @TableField("DELETE_FLAG")
    private BigDecimal deleteFlag;
}
