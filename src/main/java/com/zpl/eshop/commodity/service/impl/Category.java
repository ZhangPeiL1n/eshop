package com.zpl.eshop.commodity.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目树节点
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 22:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * 类目id
     */
    private Long categoryId;

    public <T> T execute(CategoryOperation<T> operation) throws Exception {
        return operation.doExecute(this);
    }
}
