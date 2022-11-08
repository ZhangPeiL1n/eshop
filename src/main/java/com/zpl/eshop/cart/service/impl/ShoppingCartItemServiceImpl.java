package com.zpl.eshop.cart.service.impl;

import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.service.ShoppingCartItemService;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangPeiL1n
 * @date 2022/5/28 10:37
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

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
     * @throws Exception
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        shoppingCartItemDAO.remove(id);
        return true;
    }

    /**
     * 更新购物车条目购买数量
     *
     * @param id               购物车条目id
     * @param purchaseQuantity 购买数量
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean updatePurchaseQuantity(Long id, Long purchaseQuantity) throws Exception {
        ShoppingCartItemDO item = new ShoppingCartItemDO();
        item.setId(id);
        item.setPurchaseQuantity(purchaseQuantity);
        item.setGmtModified(dateProvider.getCurrentTime());
        shoppingCartItemDAO.updateShoppingCartItem(item);
        return true;
    }
}
