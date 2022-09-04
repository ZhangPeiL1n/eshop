package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品图片
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 22:08
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsPictureDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

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
