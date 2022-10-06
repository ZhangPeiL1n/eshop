package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 货位
 *
 * @author ZhangPeiL1n
 * @date 2022/10/6 13:55
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsAllocationVO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 货位编号
     */
    private String code;

    /**
     * 货位备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
