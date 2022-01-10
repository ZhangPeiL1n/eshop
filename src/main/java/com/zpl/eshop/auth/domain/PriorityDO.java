package com.zpl.eshop.auth.domain;

import com.zpl.eshop.common.util.BeanCopierUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 权限DO类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/7 21:36
 **/
@Getter
@Setter
@ToString
public class PriorityDO {

    private static final Logger logger = LoggerFactory.getLogger(PriorityDO.class);
    /**
     * id
     */
    private Long id;
    /**
     * 权限编号
     */
    private String code;
    /**
     * 权限URL
     */
    private String url;
    /**
     * 权限备注
     */
    private String priorityComment;
    /**
     * 权限类型
     */
    private Integer priorityType;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 权限创建时间
     */
    private Date gmtCreate;
    /**
     * 权限修改时间
     */
    private Date gmtModified;

    /**
     * 克隆方法
     *
     * @param clazz 目标 class 对象
     * @param <T>   目标类型
     * @return 克隆后的对象
     */
    public <T> T clone(Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            logger.error("error", e);
        }
        BeanCopierUtils.copyProperties(this, target);
        return target;
    }
}
