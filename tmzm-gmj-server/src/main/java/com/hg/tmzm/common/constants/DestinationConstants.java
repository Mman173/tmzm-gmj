package com.hg.tmzm.common.constants;

/**
 * Destination constants (目的地/用途)
 *
 * @author hg
 */
public final class DestinationConstants {

    private DestinationConstants() {
        // Prevent instantiation
    }

    /** 备用 (Standby) */
    public static final String STANDBY = "01";

    /** 装配 (Assembly) */
    public static final String ASSEMBLY = "02";

    /** 加工 (Processing) */
    public static final String PROCESSING = "03";

    /** 报废 (Scrap) */
    public static final String SCRAP = "04";

    /** 拆辊 (Disassemble roll) */
    public static final String DISASSEMBLE_ROLL = "05";

    /** 装辊 (Assemble roll) */
    public static final String ASSEMBLE_ROLL = "06";

    /** 检验 (Inspection) */
    public static final String INSPECTION = "07";

    /** 计划上线-98 (Plan online - code 98) */
    public static final String PLAN_ONLINE_98 = "98";

    /** 计划上线-99 (Plan online - code 99) */
    public static final String PLAN_ONLINE_99 = "99";
}
