package com.zpl.eshop.wms.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 物流单DTO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/16 19:02
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticOrderDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 销售出库单id
     */
    private Long saleDeliveryOrderId;

    /**
     * 物流单号
     */
    private String logisticCode;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
