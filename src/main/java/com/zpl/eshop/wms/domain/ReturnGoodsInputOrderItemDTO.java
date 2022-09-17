package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 退货入库条目DTO对象
 *
 * @author ZhangPeiL1n
 * @date 2022/1/25 21:18
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnGoodsInputOrderItemDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 退货入库单id
     */
    private Long returnGoodsInputOrderId;

    /**
     * 商品skuId
     */
    private Long goodsSkuId;

    /**
     * 商品编码
     */
    private String goodsSkuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品销售属性
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
     * 促销活动id
     */
    private Long promotionActivityId;

    /**
     * 长
     */
    private Double goodsLength;

    /**
     * 宽
     */
    private Double goodsWidth;

    /**
     * 高
     */
    private Double goodsHeight;

    /**
     * 退货后合格商品数量
     */
    private Long qualifiedCount;

    /**
     * 退货实际到货数量
     */
    private Long arrivalCount;

    /**
     * 创建日期
     */
    private Date gmtCreate;

    /**
     * 更新日期
     */
    private Date gmtModified;

    /**
     * 退货入库单商品上架条目DTO集合
     */
    private List<ReturnGoodsInputOrderPutOnItemDTO> putOnItems;


}
