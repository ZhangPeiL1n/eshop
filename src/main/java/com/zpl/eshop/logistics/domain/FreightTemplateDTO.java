package com.zpl.eshop.logistics.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 运费模版DO
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:49
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class FreightTemplateDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 运费模板的名称
     */
    private String name;

    /**
     * 运费模板的类型
     */
    private Integer type;

    /**
     * 运费模板的规则
     */
    private String rule;

    /**
     * 运费模板的备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
