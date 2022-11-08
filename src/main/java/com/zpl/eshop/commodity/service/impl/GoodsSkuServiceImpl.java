package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.*;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.GoodsSkuService;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品Sku管理Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 18:24
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsSkuServiceImpl implements GoodsSkuService {

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 商品sku管理DAO组件
     */
    @Autowired
    private GoodsSkuDAO goodsSkuDAO;

    /**
     * 属性管理DAO组件
     */
    @Autowired
    private PropertyDAO propertyDAO;

    /**
     * 类目属性关联关系DAo组件
     */
    @Autowired
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;

    /**
     * 商品sku销售属性值管理DAO组件
     */
    @Autowired
    private GoodsSkuSalePropertyValueDAO goodsSkuSalePropertyValueDAO;

    /**
     * 库存中心接口
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private GoodsDAO goodsDAO;

    /**
     * 根据商品id查询商品sku
     *
     * @param goodsId 商品id
     * @return 商品sku
     * @throws Exception
     */
    @Override
    public List<GoodsSkuDTO> listByGoodsId(Long goodsId) throws Exception {
        List<GoodsSkuDTO> goodsSkus = ObjectUtils.convertList(goodsSkuDAO.listByGoodsId(goodsId), GoodsSkuDTO.class);
        goodsSkus.forEach(goodsSku -> {
            try {
                Long saleStockQuantity = inventoryService.getSaleStockQuantity(goodsSku.getId());
                List<GoodsSkuSalePropertyValueDTO> propertyValues = ObjectUtils.convertList(goodsSkuSalePropertyValueDAO.listByGoodsSkuId(goodsSku.getId()), GoodsSkuSalePropertyValueDTO.class);
                goodsSku.setSaleStockQuantity(saleStockQuantity);
                goodsSku.setPropertyValues(propertyValues);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return goodsSkus;
    }

    /**
     * 新增商品SKU
     *
     * @param goodsSkus 商品sku
     */
    @Override
    public void batchSave(List<GoodsSkuDTO> goodsSkus) throws Exception {
        goodsSkus.forEach(goodsSku -> {
            try {
                Long id = saveGoodsSku(goodsSku);
                batchSavePropertyValues(id, goodsSku.getPropertyValues());
                inventoryService.setSaleStockQuantity(id, goodsSku.getSaleStockQuantity());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 根据商品id删除sku
     *
     * @param goodsId 商品id
     * @throws Exception
     */
    @Override
    public void removeByGoodsId(Long goodsId) throws Exception {
        List<GoodsSkuDO> goodsSkus = goodsSkuDAO.listByGoodsId(goodsId);
        goodsSkus.forEach(goodsSku -> {
            goodsSkuSalePropertyValueDAO.removeByGoodsSkuId(goodsSku.getId());
        });
        goodsSkuDAO.removeByGoodsId(goodsId);
    }

    /**
     * 批量新增商品sku的销售属性值
     *
     * @param propertyValues 属性值
     * @throws Exception
     */
    private void batchSavePropertyValues(Long goodsSkuId, List<GoodsSkuSalePropertyValueDTO> propertyValues) throws Exception {
        propertyValues.forEach(propertyValue -> {
            try {
                propertyValue.setGoodsSkuId(goodsSkuId);
                propertyValue.setGmtCreate(dateProvider.getCurrentTime());
                propertyValue.setGmtModified(dateProvider.getCurrentTime());
                goodsSkuSalePropertyValueDAO.save(propertyValue.clone(GoodsSkuSalePropertyValueDO.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * 新增商品sku
     *
     * @param goodsSku 商品sku
     * @throws Exception
     */
    private Long saveGoodsSku(GoodsSkuDTO goodsSku) throws Exception {
        goodsSku.setSaleProperties(getSaleProperties(goodsSku.getPropertyValues()));
        goodsSku.setGmtCreate(dateProvider.getCurrentTime());
        goodsSku.setGmtModified(dateProvider.getCurrentTime());
        return goodsSkuDAO.save(goodsSku.clone(GoodsSkuDO.class));
    }

    /**
     * 获取销售属性
     *
     * @param propertyValues 属性值
     * @return 销售属性
     * @throws Exception
     */
    private String getSaleProperties(List<GoodsSkuSalePropertyValueDTO> propertyValues) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < propertyValues.size(); i++) {
            CategoryPropertyRelationshipDO relation = categoryPropertyRelationshipDAO.getById(propertyValues.get(i).getRelationId());
            PropertyDO property = propertyDAO.getPropertyById(relation.getPropertyId());
            builder.append(property.getPropertyName()).append(":").append(propertyValues.get(i).getPropertyValue());
            if (i < propertyValues.size() - 1) {
                builder.append(";");
            }
        }
        return builder.toString();
    }

    /**
     * 根据id查询商品sku
     *
     * @param id 商品skuId
     * @return 商品sku
     * @throws Exception
     */
    @Override
    public GoodsSkuDTO getById(Long id) throws Exception {
        GoodsSkuDTO goodsSku = goodsSkuDAO.getById(id).clone(GoodsSkuDTO.class);
        setGoodsRelatedFields(goodsSku);
        return goodsSku;
    }

    /**
     * 分页查询商品sku
     *
     * @param query 查询条件
     * @return 商品sku
     * @throws Exception
     */
    @Override
    public List<GoodsSkuDTO> listByPage(GoodsSkuQuery query) throws Exception {
        List<GoodsSkuDTO> goodsSkus = ObjectUtils.convertList(
                goodsSkuDAO.listByPage(query), GoodsSkuDTO.class);
        for (GoodsSkuDTO goodsSku : goodsSkus) {
            setGoodsRelatedFields(goodsSku);
        }
        return goodsSkus;
    }

    private void setGoodsRelatedFields(GoodsSkuDTO goodsSku) throws Exception {
        GoodsDO goods = goodsDAO.getById(goodsSku.getGoodsId());

        goodsSku.setGoodsName(goods.getName());
        goodsSku.setGrossWeight(goods.getGrossWeight());
        goodsSku.setGoodsLength(goods.getLength());
        goodsSku.setGoodsWidth(goods.getWidth());
        goodsSku.setGoodsHeight(goods.getHeight());
        goodsSku.setGoodsSkuCode(goodsSku.getSkuCode());
        goodsSku.setSaleStockQuantity(inventoryService
                .getSaleStockQuantity(goodsSku.getId()));
    }
}
