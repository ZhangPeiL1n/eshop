package com.zpl.eshop.common.util;

/**
 * 基础pojo类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 17:15
 **/
public abstract class AbstractObject {

    /**
     * 深拷贝方法
     *
     * @param clazz 目标类
     * @param <T>   目标类型
     * @return 拷贝结果
     */
    protected <T> T clone(Class<T> clazz) throws Exception {
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }
}
