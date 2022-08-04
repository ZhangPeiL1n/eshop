package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 类目 DO 类
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 22:08
 **/
@Data
@EqualsAndHashCode
public class CategoryDO extends AbstractObject {

    /**
     * 类目id
     */
    private Long id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目描述
     */
    private String description;

    /**
     * 是否叶子类目，1：是，0：不是
     */
    private Integer isLeaf;

    /**
     * 父类目id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
