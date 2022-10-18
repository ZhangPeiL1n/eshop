package com.zpl.eshop.logistics.service.impl;

import com.zpl.eshop.logistics.constant.FreightTemplateType;
import com.zpl.eshop.logistics.domain.FreightTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 运费计算工厂
 *
 * @author ZhangPeiL1n
 * @date 2022/10/18 11:16
 **/
@Component
public class FreightCalculatorFactory {

    /**
     * 固定运费计算器
     */
    @Autowired
    private FixedFreightCalculator fixedFreightCalculator;

    /**
     * 满X元包邮运费计算器
     */
    @Autowired
    private ReachFreeFreightCalculator reachFreeFreightCalculator;

    /**
     * 自定义规则运费计算器
     */
    @Autowired
    private CustomRuleFreightCalculator customRuleFreightCalculator;

    /**
     * 默认运费计算器
     */
    @Autowired
    private DefaultFreightCalculator defaultFreightCalculator;

    /**
     * 获取运费模板对应的运费计算器
     *
     * @param freightTemplate 运费模板
     * @return 运费计算器
     */
    public FreightCalculator get(FreightTemplateDTO freightTemplate) {
        if (FreightTemplateType.FIXED.equals(freightTemplate.getType())) {
            return fixedFreightCalculator;
        } else if (FreightTemplateType.REACH_FREE.equals(freightTemplate.getType())) {
            return reachFreeFreightCalculator;
        } else if (FreightTemplateType.CUSTOM_RULE.equals(freightTemplate.getType())) {
            return customRuleFreightCalculator;
        }
        return defaultFreightCalculator;
    }
}
