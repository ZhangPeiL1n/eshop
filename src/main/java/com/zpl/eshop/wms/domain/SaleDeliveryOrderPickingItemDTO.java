package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 销售出库单件货条目DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 18:39
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDeliveryOrderPickingItemDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 销售出库单id
     */
    private Long saleDeliveryOrderId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 拣货数量
     */
    private Long pickingCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
