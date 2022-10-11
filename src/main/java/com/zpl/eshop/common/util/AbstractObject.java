package com.zpl.eshop.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础pojo类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 17:15
 **/
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractObject {

    /**
     * 浅克隆
     *
     * @param clazz
     * @param <T>
     * @return 新对象
     * @throws Exception
     */
    public <T> T clone(Class<T> clazz) throws Exception {
        // 基本字段的浅克隆
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }

    /**
     * 浅克隆
     *
     * @param <T>
     * @return 传入的对象
     * @throws Exception
     */
    public <T> T clone(T target) throws Exception {
        // 基本字段的浅克隆
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }

    /**
     * 深克隆
     *
     * @param clazz 目标类
     * @param <T>   目标类型
     * @return 拷贝结果
     */
    public <T> T clone(Class<T> clazz, Integer cloneDirection) throws Exception {
        // 基本字段的浅克隆
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, target);

        // 完成所有 List 类型的深度克隆
        // categoryDTO
        Class<?> thisClazz = this.getClass();
        Field[] fields = thisClazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            // 如果某个字段是 List 类型的
            // filed.getType() 是 List 不是 List<Relation>
            if (field.getType() != List.class) {
                continue;
            }
            // 获取实际的 list 集合
            List<?> list = (List<?>) field.get(this);
            if (list == null || list.size() == 0) {
                continue;
            }

            // 获取集合泛型
            Class<?> listGenericClazz = getListGenericType(field);
            // 获取目标类名，也就是要拷贝的类名, eg:集合是 List<CategoryDTO> 那么 forward 的目标类型就是 CategoryVO
            Class<?> cloneTargetClazz = getCloneTargetClass(listGenericClazz, cloneDirection);
            // 将 list 集合克隆到目标 list 集合
            List<AbstractObject> clonedList = new ArrayList<>();

            // 遍历 List，继续深拷贝 List 中的元素
            cloneList(list, clonedList, cloneTargetClazz, cloneDirection);
            // 获取克隆好的 setter 方法，准备 set 构造好的 List
            Method setMethod = getSetCloneListFiledSetMethodName(field, clazz);
            setMethod.invoke(target, clonedList);
        }

        return target;
    }

    /**
     * 将一个 list 克隆到另一个list
     *
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     * @throws Exception
     */
    private void cloneList(List sourceList, List<AbstractObject> targetList, Class cloneTargetClazz, Integer cloneDirection) throws Exception {
        for (Object object : sourceList) {
            AbstractObject targetObject = (AbstractObject) object;
            AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz, cloneDirection);
            targetList.add(clonedObject);
        }
    }

    /**
     * 获取 List 的泛型类型
     *
     * @param field
     * @return
     * @throws Exception
     */
    private Class<?> getListGenericType(Field field) throws Exception {
        // genericType = List<Relation>,不是 list
        Type genericType = field.getGenericType();
        // 获取集合泛型类型
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    /**
     * 获取类名
     *
     * @param clazz
     * @param cloneDirection 克隆方向
     * @return 目标类型类名
     * @throws Exception
     */
    private Class<?> getCloneTargetClass(Class<?> clazz, Integer cloneDirection) throws Exception {
        String className = clazz.getName();

        String cloneTargetClassName = null;
        if (CloneDirection.FORWARD.equals(cloneDirection)) {
            if (className.endsWith(DomainType.VO)) {
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            } else if (className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "DO";
            }
        }
        if (CloneDirection.OPPOSITE.equals(cloneDirection)) {
            if (className.endsWith(DomainType.DO)) {
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            } else if (className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "VO";
            }
        }
        return Class.forName(cloneTargetClassName);
    }

    /**
     * 获取设置克隆好的 list 的方法名称
     *
     * @param clazz
     * @return 目标类型类名
     * @throws Exception
     */
    private Method getSetCloneListFiledSetMethodName(Field field, Class<?> clazz) throws Exception {
        String fieldName = field.getName();
        String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method setFiledMethod = null;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(setMethodName)) {
                setFiledMethod = method;
                break;
            }
        }
        return setFiledMethod;
    }
}
