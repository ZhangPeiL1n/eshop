package com.zpl.eshop.cart.domain;

import com.zpl.eshop.common.util.BeanCopierUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 购物车DTO类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/9 0:31
 **/
@Data
public class ShoppingCartDTO {

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartDTO.class);

    /**
     * id
     */
    private Long id;

    /**
     * 用户帐号id
     */
    private Long userAccountId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 购物车条目集合
     */
    private List<ShoppingCartItemDTO> shoppingCartItemDTOList;

    /**
     * 深拷贝方法
     *
     * @param clazz 目标类
     * @param <T>   目标类型
     * @return 拷贝结果
     */
    public <T> T clone(Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            BeanCopierUtils.copyProperties(this, target);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return target;
    }

}
