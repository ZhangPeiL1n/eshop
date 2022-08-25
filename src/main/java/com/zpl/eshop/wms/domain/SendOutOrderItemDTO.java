package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 发货单条目DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 18:30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SendOutOrderItemDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 发货单id
     */
    private Long sendOutOrderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 商品sku编号
     */
    private String goodsSkuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 销售属性
     */
    private String saleProperties;

    /**
     * 商品毛重
     */
    private Double goodsGrossWeight;

    /**
     * 购买数量
     */
    private Long purchaseQuantity;

    /**
     * 购买价格
     */
    private Double purchasePrice;

    /**
     * 商品长度
     */
    private Double goodsLength;

    /**
     * 商品宽度
     */
    private Double goodsWidth;

    /**
     * 商品高度
     */
    private Double goodsHeight;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
