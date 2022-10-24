package com.zpl.eshop.wms.domain;

import lombok.Data;

/**
 * 退户入库单查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/10/24 22:16
 **/
@Data
public class ReturnGoodsInputOrderQuery {
    /**
     * 分页查询起始位置
     */
    private Integer offset;
    /**
     * 每页显示的数据条数
     */
    private Integer size;
}
