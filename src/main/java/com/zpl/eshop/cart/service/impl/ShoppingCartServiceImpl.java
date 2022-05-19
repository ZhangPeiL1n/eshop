package com.zpl.eshop.cart.service.impl;

import com.zpl.eshop.cart.dao.ShoppingCartDAO;
import com.zpl.eshop.cart.dao.ShoppingCartItemDAO;
import com.zpl.eshop.cart.domain.ShoppingCartDO;
import com.zpl.eshop.cart.domain.ShoppingCartDTO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDO;
import com.zpl.eshop.cart.domain.ShoppingCartItemDTO;
import com.zpl.eshop.cart.service.ShoppingCartService;
import com.zpl.eshop.commodity.domain.GoodsSkuDTO;
import com.zpl.eshop.commodity.service.CommodityFacadeService;
import com.zpl.eshop.inventory.service.InventoryFacadeService;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 商品中心对外接口Service组件
     */
    @Autowired
    private CommodityFacadeService commodityFacadeService;

    /**
     * 库存中心对外接口Service组件
     */
    @Autowired
    private InventoryFacadeService inventoryFacadeService;

    /**
     * 促销中心对外接口Service组件
     */
    @Autowired
    private PromotionFacadeService promotionFacadeService;

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

    /**
     * 查看用户购物车中的数据
     *
     * @param userAccountId 用户帐号id
     * @return 购物车DTO对象
     */
    @Override
    public ShoppingCartDTO getShoppingCartDTOByUserAccountId(Long userAccountId) {
        try {
            ShoppingCartDO shoppingCart = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);
            if (shoppingCart == null) {
                return new ShoppingCartDTO();
            }
            ShoppingCartDTO shoppingCartDTO = shoppingCart.clone(ShoppingCartDTO.class);
            // 查询购物车条目
            List<ShoppingCartItemDO> shoppingCartItemDOList = shoppingCartItemDAO.listShoppingCartItemByCartId(shoppingCartDTO.getId());
            if (shoppingCartItemDOList == null || shoppingCartItemDOList.size() == 0) {
                return shoppingCartDTO;
            }
            List<ShoppingCartItemDTO> shoppingCartItemDTOList = new ArrayList<>();
            // 为购物车条目填充相关数据
            for (ShoppingCartItemDO shoppingCartItemDO : shoppingCartItemDOList) {
                ShoppingCartItemDTO item = shoppingCartItemDO.clone(ShoppingCartItemDTO.class);
                // 给购物车条目填充商品数据
                setGoodsRelatedData(item);
                // 给购物车条目填充库存数据
                setStockRelatedData(item);
                // 给购物车条目填充促销数据
                setPromotionRelatedData(item);
                // 添加购物车条目到集合中
                shoppingCartItemDTOList.add(item);
            }
            shoppingCartDTO.setShoppingCartItemDTOList(shoppingCartItemDTOList);
            return shoppingCartDTO;
        } catch (Exception e) {
            logger.error("error", e);
            return new ShoppingCartDTO();
        }
    }

    /**
     * 给购物车填充数据
     *
     * @param item 购物车条目
     * @throws Exception
     */
    private void setGoodsRelatedData(ShoppingCartItemDTO item) throws Exception {
        GoodsSkuDTO goodsSkuDTO = commodityFacadeService.getGoodsSkuById(item.getGoodsSkuId());
        item.setGoodsId(goodsSkuDTO.getGoodsId());
        item.setGoodsName(goodsSkuDTO.getGoodsName());
        item.setGoodsSkuCode(goodsSkuDTO.getGoodsSkuCode());
        item.setGoodsLength(goodsSkuDTO.getGoodsLength());
        item.setGoodsWidth(goodsSkuDTO.getGoodsWidth());
        item.setGoodsHeight(goodsSkuDTO.getGoodsHeight());
        item.setGrossWeight(goodsSkuDTO.getGrossWeight());
        item.setSalePrice(goodsSkuDTO.getSalePrice());
        item.setSaleProperties(goodsSkuDTO.getSaleProperties());
    }

    /**
     * 给购物车条目设置库存相关的数据
     *
     * @param item 购物车条目
     * @throws Exception
     */
    private void setStockRelatedData(ShoppingCartItemDTO item) throws Exception {
        Long saleStockQuantity = inventoryFacadeService.getSaleStockQuantity(item.getGoodsSkuId());
        item.setSaleStockQuantity(saleStockQuantity);
    }

    /**
     * 设置促销相关数据
     *
     * @param item 购物车条目
     * @throws Exception
     */
    private void setPromotionRelatedData(ShoppingCartItemDTO item) throws Exception {
        List<PromotionActivityDTO> promotionActivityDTOList = promotionFacadeService.listPromotionActivityByGoodsId(item.getGoodsId());
        item.setPromotionActivityDTOList(promotionActivityDTOList);
    }
}