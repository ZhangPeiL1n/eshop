package com.zpl.eshop.order.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 订单操作日志
 *
 * @author ZhangPeiL1n
 * @date 2022/9/4 14:26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderOperateLogDTO extends AbstractObject {

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderInfoId;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 操作内容
     */
    private String operateContent;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
