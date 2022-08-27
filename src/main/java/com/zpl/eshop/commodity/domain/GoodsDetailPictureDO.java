package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品详情图片
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 22:12
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsDetailPictureDO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 商品详情id
     */
    private Long goodsDetailId;

    /**
     * 商品图片路径
     */
    private String picturePath;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
