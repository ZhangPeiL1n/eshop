package com.zpl.eshop.logistics.constant;

/**
 * 运费模版类型
 *
 * @author ZhangPeiL1n
 * @date 2022/10/18 11:19
 **/
public class FreightTemplateType {

    /**
     * 固定运费
     */
    public static final Integer FIXED = 1;

    /**
     * 满X元包邮
     */
    public static final Integer REACH_FREE = 2;

    /**
     * 自定义规则
     */
    public static final Integer CUSTOM_RULE = 3;

    private FreightTemplateType() {

    }
}
