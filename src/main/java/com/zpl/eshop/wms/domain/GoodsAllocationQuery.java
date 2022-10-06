package com.zpl.eshop.wms.domain;

import lombok.Data;

/**
 * 货位查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 14:00
 **/
@Data
public class GoodsAllocationQuery {

    /**
     * 分页查询起始位置
     */
    private Integer offset;

    /**
     * 每页显示的数据条数
     */
    private Integer size;

    /**
     * 货位编号
     */
    private String code;
}
