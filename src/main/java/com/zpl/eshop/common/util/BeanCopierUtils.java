package com.zpl.eshop.common.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanCopier 工具类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/9 17:10
 **/
public class BeanCopierUtils {

    /**
     * BeanCopier 缓存
     */
    public static Map<String, BeanCopier> beanCopierCacheMap = new HashMap<>();

    /**
     * 将 source 对象的属性拷贝到 target 对象中去
     *
     * @param source source 对象
     * @param target target 对象
     */
    public static void copyProperties(Object source, Object target) {
        String cacheKey = source.getClass().toString() + target.getClass().toString();

        BeanCopier beanCopier = null;
        if (!beanCopierCacheMap.containsKey(cacheKey)) {
            synchronized (BeanCopierUtils.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey)) {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierCacheMap.put(cacheKey, beanCopier);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey);
        }

        beanCopier.copy(source, target, null);
    }
}
