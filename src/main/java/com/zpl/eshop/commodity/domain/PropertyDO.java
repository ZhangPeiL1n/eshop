package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.BeanCopierUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 商品属性DO
 *
 * @author ZhangPeiL1n
 * @date 2022/1/19 21:08
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PropertyDO {

    private final Logger logger = LoggerFactory.getLogger(PropertyDO.class);

    /**
     * id
     */
    private Long id;

    /**
     * 商品属性
     */
    private String propertyName;

    /**
     * 商品描述
     */
    private String propertyDesc;

    /**
     * 输入方式
     */
    private Integer inputType;

    /**
     * 输入可选值
     */
    private String inputValues;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public <T> T clone(Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            BeanCopierUtils.copyProperties(this, target);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return target;
    }

}
