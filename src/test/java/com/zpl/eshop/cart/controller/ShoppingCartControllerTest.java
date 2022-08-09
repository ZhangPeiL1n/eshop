package com.zpl.eshop.cart.controller;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.cart.domain.AddShoppingCartItemQuery;
import com.zpl.eshop.cart.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 购物车管理模块Controller组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 16:28
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    /**
     * 购物车管理模块 Service 组件
     */
    @MockBean
    private ShoppingCartService shoppingCartService;

    /**
     * 注入一个 MockMvc，模拟对 controller 发起 http 请求
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 测试添加购物车商品条目
     */
    @Test
    public void testAddShoppingCartItem() throws Exception {
        Long userAccountId = 1L;
        Long goodsSkuId = 1L;

        AddShoppingCartItemQuery query = new AddShoppingCartItemQuery();
        query.setUserAccountId(userAccountId);
        query.setGoodsSkuId(goodsSkuId);
        Mockito.when(shoppingCartService.addShoppingCartItem(userAccountId, goodsSkuId)).thenReturn(true);

        // 调用 http 接口请求以及比较返回结果
        mockMvc.perform(MockMvcRequestBuilders.post("/cart/item/add")
                        .contentType("application/json")
                        .content(JSONObject.toJSONString(query)))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}
