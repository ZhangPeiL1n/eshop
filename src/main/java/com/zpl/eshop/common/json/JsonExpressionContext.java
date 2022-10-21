package com.zpl.eshop.common.json;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * json表达式上下文
 *
 * @author ZhangPeiL1n
 * @date 2022/10/21 22:43
 **/
@Data
public class JsonExpressionContext {

    /**
     * 目标json
     */
    private final JSONObject targetJson;

    /**
     * 当前节点
     */
    private JSONObject currentJsonNode;

    public JsonExpressionContext(JSONObject targetJson) {
        this.targetJson = targetJson;
    }
}
