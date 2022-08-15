package com.zpl.eshop.commodity.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 品牌
 *
 * @author ZhangPeiL1n
 * @date 2022/8/8 21:21
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class BrandDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 别名
     */
    private String aliasName;

    /**
     * logo路径
     */
    private String logoPath;

    /**
     * 品牌介绍
     */
    private String introduction;

    /**
     * 品牌授权凭证
     */
    private String authVoucherPath;

    /**
     * 品牌所在地
     */
    private String location;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
