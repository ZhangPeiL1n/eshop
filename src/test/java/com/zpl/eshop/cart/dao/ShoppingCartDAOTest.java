package com.zpl.eshop.cart.dao;

import com.zpl.eshop.cart.domain.ShoppingCartDO;
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

/**
 * 购物车管理模块的DAO组件的单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/22 23:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class ShoppingCartDAOTest {

    /**
     * 购物车管理模块DAO组件
     */
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * 测试新增购物车
     */
    @Test
    public void testSaveShoppingCart() throws ParseException {
        ShoppingCartDO shoppingCartDO = createShoppingCart();
        Long shoppingCartId = shoppingCartDO.getId();
        Assert.assertNotNull(shoppingCartId);
        Assert.assertThat(shoppingCartId, Matchers.greaterThan(0L));
    }

    /**
     * 测试根据用户帐号id查询购物车
     */
    @Test
    public void testGetShoppingCartByUserAccountId() throws ParseException {
        ShoppingCartDO shoppingCartDO = createShoppingCart();

        ShoppingCartDO resultShoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(shoppingCartDO.getUserAccountId());
        // 断言结果
        Assert.assertEquals(shoppingCartDO, resultShoppingCartDO);
    }

    /**
     * 创建购物车
     *
     * @return 购物车DO
     */
    public ShoppingCartDO createShoppingCart() throws ParseException {
        Long userAccountId = 1L;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        shoppingCartDO.setUserAccountId(userAccountId);
        shoppingCartDO.setGmtCreate(currentTime);
        shoppingCartDO.setGmtModified(currentTime);
        shoppingCartDAO.saveShoppingCart(shoppingCartDO);
        return shoppingCartDO;
    }
}
