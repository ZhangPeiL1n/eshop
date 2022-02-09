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
            ShoppingCartDO shoppingCartDO = shoppingCartDAO.getShoppingCartByUserAccountId(userAccountId);
            if (shoppingCartDO == null) {
                return new ShoppingCartDTO();
            }
            ShoppingCartDTO shoppingCartDTO = shoppingCartDO.clone(ShoppingCartDTO.class);
            List<ShoppingCartItemDO> shoppingCartItemDOList = shoppingCartItemDAO.listShoppingCartItemByCartSkuId(shoppingCartDTO.getId());
            if (shoppingCartItemDOList == null || shoppingCartItemDOList.size() == 0) {
                return shoppingCartDTO;
            }
            List<ShoppingCartItemDTO> shoppingCartItemDTOList = new ArrayList<>();
            // 处理购物车条目数据
            for (ShoppingCartItemDO shoppingCartItemDO : shoppingCartItemDOList) {
                ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemDO.clone(ShoppingCartItemDTO.class);
                // 给购物车条目填充商品数据
                GoodsSkuDTO goodsSkuDTO = commodityFacadeService.getGoodsSkuById(shoppingCartItemDTO.getGoodsSkuId());
                shoppingCartItemDTO.setGoodsId(goodsSkuDTO.getGoodsId());
                shoppingCartItemDTO.setGoodsName(goodsSkuDTO.getGoodsName());
                shoppingCartItemDTO.setGoodsSkuCode(goodsSkuDTO.getGoodsSkuCode());
                shoppingCartItemDTO.setGoodsLength(goodsSkuDTO.getGoodsLength());
                shoppingCartItemDTO.setGoodsWidth(goodsSkuDTO.getGoodsWidth());
                shoppingCartItemDTO.setGoodsHeight(goodsSkuDTO.getGoodsHeight());
                shoppingCartItemDTO.setGrossWeight(goodsSkuDTO.getGrossWeight());
                shoppingCartItemDTO.setSalePrice(goodsSkuDTO.getSalePrice());
                shoppingCartItemDTO.setSaleProperties(goodsSkuDTO.getSaleProperties());

                // 给购物车条目填充库存数据
                Long saleStockQuantity = inventoryFacadeService.getSaleStockQuantity(shoppingCartItemDTO.getGoodsSkuId());
                shoppingCartItemDTO.setSaleStockQuantity(saleStockQuantity);

                // 给购物车条目填充促销数据
                List<PromotionActivityDTO> promotionActivityDTOList = promotionFacadeService.listPromotionActivityByGoodsId(shoppingCartItemDTO.getGoodsId());
                shoppingCartItemDTO.setPromotionActivityDTOList(promotionActivityDTOList);

                // 添加购物车条目到集合中
                shoppingCartItemDTOList.add(shoppingCartItemDTO);
            }
            shoppingCartDTO.setShoppingCartItemDTOList(shoppingCartItemDTOList);
            return shoppingCartDTO;
        } catch (Exception e) {
            logger.error("error", e);
            return new ShoppingCartDTO();
        }
    }
}
