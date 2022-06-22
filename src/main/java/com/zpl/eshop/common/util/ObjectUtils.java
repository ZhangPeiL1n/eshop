package com.zpl.eshop.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象工具类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 17:33
 **/
public class ObjectUtils {

    /**
     * 转换集合
     *
     * @param sourceList  源集合
     * @param targetClazz 目标集合元素类型
     * @return 转换后的集合
     */
    public static <T> List<T> convertList(List<? extends AbstractObject> sourceList, Class<T> targetClazz) throws Exception {
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (AbstractObject abstractObject : sourceList) {
            targetList.add(abstractObject.clone(targetClazz));
        }
        return targetList;
    }


    /**
     * 转换集合-深度克隆
     *
     * @param sourceList     源集合
     * @param targetClazz    目标集合元素类型
     * @param cloneDirection 克隆方向
     * @return 转换后的集合
     */
    public static <T> List<T> convertList(List<? extends AbstractObject> sourceList, Class<T> targetClazz, Integer cloneDirection) throws Exception {
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (AbstractObject abstractObject : sourceList) {
            targetList.add(abstractObject.clone(targetClazz, cloneDirection));
        }
        return targetList;
    }
}
