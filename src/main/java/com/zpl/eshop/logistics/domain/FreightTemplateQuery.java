package com.zpl.eshop.logistics.domain;

import lombok.Data;

/**
 * 运费模版查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/8 20:51
 **/
@Data
public class FreightTemplateQuery {
    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 运费模板名称
     */
    private String name;

    /**
     * 运费模板类型
     */
    private Integer type;
}
