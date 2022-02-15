package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 角色DO对象
 *
 * @author ZhangPeiL1n
 * @date 2022/2/15 15:41
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class RoleDO extends AbstractObject {

    private static final Logger logger = LoggerFactory.getLogger(RoleDO.class);

    /**
     * id
     */
    private Long id;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色说明备注
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
