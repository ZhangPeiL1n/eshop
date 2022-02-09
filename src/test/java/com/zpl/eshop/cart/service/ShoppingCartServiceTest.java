package com.zpl.eshop.cart.service;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 购物车模块Service组件测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 11:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ShoppingCartServiceTest {

    /**
     * 购物车管理模块Service组件
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 购物车管理模块DAO组件
     */
    @MockBean
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * 购物车条目管理模块DAO组件
     */
    @MockBean
    private ShoppingCartItemDAO shoppingCartItemDAO;

    /**
     * 测试添加购物车条目
     */
    @Test
    public void testAddShoppingCartItem() throws Exception {
        // 准备一些参数
        Long userAccountId = 1L;
        Long goodsSkuId = 1L;
        ShoppingCartDO shoppingCartDO = createShoppingCart(userAccountId);
        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(shoppingCartDO.getId(), goodsSkuId);
        // mock 一下两个 DAO 的操作
        Mockito.when(shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId)).thenReturn(shoppingCartDO);
        Mockito.when(shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartDO.getId(), goodsSkuId)).thenReturn(shoppingCartItemDO);
        Mockito.when(shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO)).thenReturn(true);
        // 执行 service 方法
        Boolean addShoppingCartItemResult = shoppingCartService.addShoppingCartItem(userAccountId, goodsSkuId);

        // 执行断言
        Assert.assertTrue(addShoppingCartItemResult);
        Mockito.verify(shoppingCartDAO, Mockito.times(1)).getShoppingCartByUserAccountId(userAccountId);
        Mockito.verify(shoppingCartItemDAO, Mockito.times(1)).getShoppingCartItemByGoodsSkuId(shoppingCartDO.getId(), goodsSkuId);
        Mockito.verify(shoppingCartItemDAO, Mockito.times(1)).updateShoppingCartItem(shoppingCartItemDO);
    }

    /**
     * 创建一个购物车DO对象
     *
     * @return 购物车对象
     */
    public ShoppingCartDO createShoppingCart(Long userAccountId) throws ParseException {
        Long id = 1L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setId(id);
        shoppingCartDO.setUserAccountId(userAccountId);
        shoppingCartDO.setGmtCreate(currentTime);
        shoppingCartDO.setGmtModified(currentTime);
        return shoppingCartDO;
    }

    /**
     * 创建一个购物车条目DO对象
     *
     * @param shoppingCartId 购物车id
     * @param goodsSkuId     商品skuId
     * @return 购物车条目对象
     */
    public ShoppingCartItemDO createShoppingCartItem(Long shoppingCartId, Long goodsSkuId) throws ParseException {
        Long id = 1L;
        Long purchaseQuantity = 1L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        ShoppingCartItemDO shoppingCartItemDO = new ShoppingCartItemDO();
        shoppingCartItemDO.setId(id);
        shoppingCartItemDO.setShoppingCartId(shoppingCartId);
        shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
        shoppingCartItemDO.setPurchaseQuantity(purchaseQuantity);
        shoppingCartItemDO.setGmtCreate(currentTime);
        shoppingCartItemDO.setGmtModified(currentTime);

        return shoppingCartItemDO;
    }
}
