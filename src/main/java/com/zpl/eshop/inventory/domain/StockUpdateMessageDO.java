package com.zpl.eshop.inventory.domain;

import lombok.Data;

import java.util.Date;

/**
 * 库存更新消息DO类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:38
 **/
@Data
public class StockUpdateMessageDO {

    /**
     * id
     */
    private Long id;

    /**
     * 库存更新消息消息id
     */
    private String messageId;

    /**
     * 库存更新操作
     */
    private Integer operation;

    /**
     * 参数对象：json格式
     */
    private String parameter;

    /**
     * 参数类型
     */
    private String parameterClazz;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
