package com.zpl.eshop.common.json;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 用于组合别的表达式的Json表达式
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 22:45
 **/
public class CompositeJsonExpression implements JsonExpression {

    /**
     * json节点名称
     */
    private final String jsonNodeName;

    /**
     * 子表达式
     */
    @Getter
    @Setter
    private JsonExpression childJsonExpression;

    public CompositeJsonExpression(String jsonNodeName) {
        this.jsonNodeName = jsonNodeName;
    }

    @Override
    public Object interpret(JsonExpressionContext context) throws Exception {
        JSONObject currentJsonNode = context.getCurrentJsonNode();

        if (currentJsonNode == null) {
            JSONObject targetJson = context.getTargetJson();
            currentJsonNode = targetJson.getJSONObject(jsonNodeName);
            context.setCurrentJsonNode(currentJsonNode);
        } else {
            currentJsonNode = currentJsonNode.getJSONObject(jsonNodeName);
            context.setCurrentJsonNode(currentJsonNode);
        }

        return childJsonExpression.interpret(context);
    }
}
