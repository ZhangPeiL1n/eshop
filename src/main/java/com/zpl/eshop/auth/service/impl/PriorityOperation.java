package com.zpl.eshop.auth.service.impl;

/**
 * 权限操作接口
 *
 * @author ZhangPeiL1n
 */
public interface PriorityOperation<T> {

    /**
     * 执行这个操作
     *
     * @param priority 权限
     * @return
     * @throws Exception
     */
    T doExecute(Priority priority) throws Exception;

}
