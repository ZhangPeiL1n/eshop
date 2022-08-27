package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品DO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/25 21:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 商品SPU编号
     */
    private String code;

    /**
     * 商品副名称
     */
    private String subName;

    /**
     * 商品毛重
     */
    private Double grossWeight;

    /**
     * 长
     */
    private Double length;

    /**
     * 宽
     */
    private Double width;

    /**
     * 高
     */
    private Double height;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 服务保证
     */
    private String serviceGuarantees;

    /**
     * 包装清单
     */
    private String packageList;

    /**
     * 运费模版id
     */
    private Long freightTemplateId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
