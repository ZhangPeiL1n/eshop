package com.zpl.eshop.cart.service.impl;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZhangPeiL1n
 * @date 2022/1/20 22:48
 **/
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 购物车管理模块DAO组件
     */
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;
    /**
     * 购物车条目管理模块DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);


    /**
     * 添加购物车条目
     *
     * @param userAccountId 用户帐号id
     * @param goodsSkuId    商品条目
     * @return 处理结果
     */
    @Override
    public Boolean addShoppingCartItem(Long userAccountId, Long goodsSkuId) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        try {
            // 根据用户帐号id查找一下购物车
            ShoppingCartDO shoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);
            // 如果购物车不存在，则创建一个购物车
            if (shoppingCartDO == null) {
                shoppingCartDO = new ShoppingCartDO();
                shoppingCartDO.setUserAccountId(userAccountId);
                shoppingCartDO.setGmtCreate(currentTime);
                shoppingCartDO.setGmtModified(currentTime);
                shoppingCartDAO.saveShoppingCart(shoppingCartDO);
            }

            // 查询一下购物车中是否存在这个商品条目
            ShoppingCartItemDO shoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartDO.getId(), goodsSkuId);
            // 如果不存在就新增
            if (shoppingCartItemDO == null) {
                shoppingCartItemDO = new ShoppingCartItemDO();
                shoppingCartItemDO.setShoppingCartId(shoppingCartDO.getId());
                shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
                shoppingCartItemDO.setPurchaseQuantity(1L);
                shoppingCartItemDO.setGmtCreate(currentTime);
                shoppingCartItemDO.setGmtModified(currentTime);
                shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItemDO);
                // 如果存在则购买数量 +1
            } else {
                shoppingCartItemDO.setPurchaseQuantity(shoppingCartItemDO.getPurchaseQuantity() + 1L);
                shoppingCartItemDO.setGmtModified(currentTime);
                shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO);
            }
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
