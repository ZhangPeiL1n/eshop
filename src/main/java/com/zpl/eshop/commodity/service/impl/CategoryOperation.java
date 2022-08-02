package com.zpl.eshop.commodity.service.impl;

/**
 * 类目操作
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 22:34
 **/
public interface CategoryOperation<T> {

    /**
     * 执行类目操作
     *
     * @param category 类目树节点
     */
    T doExecute(Category category) throws Exception;
}
