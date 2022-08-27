package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品详情
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 22:04
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsDetailDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品详情内容
     */
    private String detailContent;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
