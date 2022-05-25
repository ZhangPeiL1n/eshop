package com.zpl.eshop.common.util;

/**
 * 克隆方向
 *
 * @author ZhangPeiL1n
 * @date 2022/5/25 21:48
 **/
public class CloneDirection {

    /**
     * 正向克隆：从 VO -> DTO,DTO -> DO
     */
    public static final Integer FORWARD = 1;

    /**
     * 反向克隆：从 DO -> DTO,DTO -> VO
     */
    public static final Integer OPPOSITE = 2;

    private CloneDirection() {
    }
}
