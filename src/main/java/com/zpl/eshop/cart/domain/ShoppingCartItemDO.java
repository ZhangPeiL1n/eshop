package com.zpl.eshop.cart.domain;

import com.zpl.eshop.common.util.BeanCopierUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * 购物车条目类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/20 21:55
 **/
@Data
public class ShoppingCartItemDO {

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartItemDO.class);
    /**
     * id
     */
    private Long id;

    /**
     * 购物车id
     */
    private Long shoppingCartId;

    /**
     * 商品SKUid
     */
    private Long goodsSkuId;

    /**
     * 购买数量
     */
    private Long purchaseQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

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
