package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 属性分组DO
 *
 * @author ZhangPeiL1n
 * @date 2022/5/17 23:08
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyGroupDO extends AbstractObject {

    /**
     * id
     */
    private Long id;
    /**
     * 属性分组名称
     */
    private String name;
    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
