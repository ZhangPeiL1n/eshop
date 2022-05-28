package com.zpl.eshop.cart.service.impl;

import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.service.ShoppingCartItemService;
import com.zpl.eshop.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 10:37
 **/
@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {


    private final Logger logger = LoggerFactory.getLogger(ShoppingCartItemServiceImpl.class);

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 购物车条目管理DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;

    /**
     * 删除购物车条目
     *
     * @param id 购物车条目id
     * @return 处理结果
     */
    @Override
    public Boolean remove(Long id) {
        try {
            return shoppingCartItemDAO.remove(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 更新购物车条目购买数量
     *
     * @param id               购物车条目id
     * @param purchaseQuantity 购买数量
     * @return 处理结果
     */
    @Override
    public Boolean updatePurchaseQuantity(Long id, Long purchaseQuantity) {
        try {
            ShoppingCartItemDO item = new ShoppingCartItemDO();
            item.setId(id);
            item.setPurchaseQuantity(purchaseQuantity);
            item.setGmtModified(dateProvider.getCurrentTime());
            return shoppingCartItemDAO.updateShoppingCartItem(item);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
