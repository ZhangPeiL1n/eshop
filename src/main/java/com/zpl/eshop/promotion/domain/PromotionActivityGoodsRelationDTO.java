package com.zpl.eshop.promotion.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 促销活动和商品的关联关系
 *
 * @author ZhangPeiL1n
 * @date 2022/8/21 17:50
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionActivityGoodsRelationDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 促销活动id
     */
    private Long promotionActivityId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
