package com.zpl.eshop.common.json;

/**
 * json表达式接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 22:43
 **/
public interface JsonExpression {

    /**
     * 解释表达式
     *
     * @param context 上下文
     * @return 结果
     * @throws Exception
     */
    Object interpret(JsonExpressionContext context) throws Exception;
}
