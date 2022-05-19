package com.zpl.eshop.cart.dao;

import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车条目DAO测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 0:18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ShoppingCartItemDAOTest {

    /**
     * 购物车条目管理模块的DAO组件
     */
    @Autowired
    private ShoppingCartItemDAO shoppingCartItemDAO;

    /**
     * 测试新增购物车条目
     */
    @Test
    public void testSaveShoppingCartItem() throws ParseException {
        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(1L, 1L, 1L);
        Long shoppingCartItemId = shoppingCartItemDO.getId();
        Assert.assertNotNull(shoppingCartItemId);
        Assert.assertThat(shoppingCartItemId, Matchers.greaterThan(0L));
    }

    /**
     * 测试根据购物车和商品id查询购物车条目
     */
    @Test
    public void testGetShoppingCartItemByGoodsSkuId() throws ParseException {
        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(1L, 2L, 1L);
        ShoppingCartItemDO resultShoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartItemDO.getShoppingCartId(), shoppingCartItemDO.getGoodsSkuId());
        Assert.assertEquals(shoppingCartItemDO, resultShoppingCartItemDO);
    }

    /**
     * 测试更新购物车条目
     */
    @Test
    public void testUpdateShoppingCartItem() throws ParseException {
        ShoppingCartItemDO shoppingCartItemDO = createShoppingCartItem(1L, 2L, 3L);
        Long newQuantity = shoppingCartItemDO.getPurchaseQuantity() + 1L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newGmtModified = dateFormat.parse(dateFormat.format(new Date()));

        shoppingCartItemDO.setPurchaseQuantity(newQuantity);
        shoppingCartItemDO.setGmtModified(newGmtModified);

        shoppingCartItemDAO.updateShoppingCartItem(shoppingCartItemDO);

        ShoppingCartItemDO resultShoppingCartItemDO = shoppingCartItemDAO.getShoppingCartItemByGoodsSkuId(shoppingCartItemDO.getShoppingCartId(), shoppingCartItemDO.getGoodsSkuId());
        Assert.assertEquals(newQuantity, resultShoppingCartItemDO.getPurchaseQuantity());
        Assert.assertEquals(newGmtModified, resultShoppingCartItemDO.getGmtModified());
    }

    @Test
    public void testListShoppingCartItemByCartId() throws ParseException {
        Long cartId = 1L;
        Map<Long, ShoppingCartItemDO> items = new HashMap<>();
        ShoppingCartItemDO item;
        item = createShoppingCartItem(cartId, 1L, 3L);
        items.put(item.getId(), item);
        item = createShoppingCartItem(cartId, 2L, 44L);
        items.put(item.getId(), item);
        item = createShoppingCartItem(cartId, 3L, 5L);
        items.put(item.getId(), item);

        List<ShoppingCartItemDO> resultItems = shoppingCartItemDAO.listShoppingCartItemByCartId(cartId);

        Assert.assertEquals(3, resultItems.size());
        for (ShoppingCartItemDO resultItem : resultItems) {
            ShoppingCartItemDO targetItem = items.get(resultItem.getId());
            Assert.assertEquals(targetItem, resultItem);
        }
    }

    public ShoppingCartItemDO createShoppingCartItem(Long shoppingCartId, Long goodsSkuId, Long purchaseQuantity) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        ShoppingCartItemDO shoppingCartItemDO = new ShoppingCartItemDO();
        shoppingCartItemDO.setShoppingCartId(shoppingCartId);
        shoppingCartItemDO.setGoodsSkuId(goodsSkuId);
        shoppingCartItemDO.setPurchaseQuantity(purchaseQuantity);
        shoppingCartItemDO.setGmtCreate(currentTime);
        shoppingCartItemDO.setGmtModified(currentTime);
        shoppingCartItemDAO.saveShoppingCartItem(shoppingCartItemDO);
        return shoppingCartItemDO;
    }
}
