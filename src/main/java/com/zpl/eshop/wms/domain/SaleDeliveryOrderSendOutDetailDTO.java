package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 销售出库单发货明细
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 18:43
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryOrderSendOutDetailDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 销售出库单条目id
     */
    private Long saleDeliveryOrderItemId;

    /**
     * 商品货位库存明细id
     */
    private Long goodsAllocationStockDetailId;

    /**
     * 发货数量
     */
    private Long sendOutCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
