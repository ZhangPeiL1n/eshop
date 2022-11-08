package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.constant.GoodsStatus;
import com.zpl.eshop.commodity.dao.*;
import com.zpl.eshop.commodity.domain.*;
import com.zpl.eshop.commodity.service.GoodsService;
import com.zpl.eshop.commodity.state.GoodsStateManager;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品管理模块service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 21:24
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    /**
     * 商品管理模块DAO组件
     */
    @Autowired
    private GoodsDAO goodsDAO;

    /**
     * 商品状态管理器
     */
    @Autowired
    private GoodsStateManager goodsStateManager;

    /**
     * 商品图片管理模块DAO组件
     */
    @Autowired
    private GoodsPictureDAO goodsPictureDAO;

    /**
     * 商品详情管理DAO组件
     */
    @Autowired
    private GoodsDetailDAO goodsDetailDAO;

    /**
     * 商品详情图片管理DAO组件
     */
    @Autowired
    private GoodsDetailPictureDAO goodsDetailPictureDAO;

    /**
     * 商品属性值管理DAO组件
     */
    @Autowired
    private GoodsPropertyValueDAO goodsPropertyValueDAO;

    /**
     * 商品sku管理DAO
     */
    @Autowired
    private GoodsSkuDAO goodsSkuDAO;

    /**
     * 商品sku销售属性管理DAO
     */
    @Autowired
    private GoodsSkuSalePropertyValueDAO goodsSkuSalePropertyValueDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品集合
     */
    @Override
    public List<GoodsDTO> listByPage(GoodsQuery query) throws Exception {
        List<GoodsDO> goods = goodsDAO.listByPage(query);
        return ObjectUtils.convertList(goods, GoodsDTO.class);
    }

    /**
     * 根据id查询商品
     *
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    @Override
    public GoodsDTO getById(Long id) throws Exception {
        return goodsDAO.getById(id).clone(GoodsDTO.class);
    }

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return 商品id
     * @throws Exception
     */
    @Override
    public Long save(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.UNKNOWN);
        goods.setGmtCreate(dateProvider.getCurrentTime());
        goods.setGmtModified(dateProvider.getCurrentTime());
        Long goodsId = goodsDAO.save(goods.clone(GoodsDO.class));
        goods.setId(goodsId);
        goodsStateManager.create(goods);
        return goodsId;
    }

    /**
     * 更新商品
     *
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public Boolean update(GoodsDTO goods) throws Exception {
        if (!goodsStateManager.canEdit(goods)) {
            return false;
        }
        goodsDAO.update(goods.clone(GoodsDO.class));
        goodsStateManager.edit(goods);
        return true;
    }

    /**
     * 审核商品
     *
     * @param goodsId       商品id
     * @param approveResult 审核结果
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean approve(Long goodsId, Integer approveResult) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canApprove(goods)) {
            return false;
        }
        goodsStateManager.approve(goods, approveResult);
        return null;
    }

    /**
     * 上架商品
     *
     * @param goodsId 商品id
     * @return 上架结果
     * @throws Exception
     */
    @Override
    public Boolean putOnShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canPutOnShelves(goods)) {
            return false;
        }
        goodsStateManager.putOnShelves(goods);
        return true;
    }

    /**
     * 下架商品
     *
     * @param goodsId 商品
     * @return 下架结果
     */
    @Override
    public Boolean pullOffShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canPullOffShelves(goods)) {
            return false;
        }
        goodsStateManager.pullOffShelves(goods);
        return true;
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return 删除结果
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        GoodsDTO goods = goodsDAO.getById(id).clone(GoodsDTO.class);
        if (!goodsStateManager.canRemove(goods)) {
            return false;
        }
        goodsPictureDAO.removeByGoodsId(id);
        GoodsDetailDO goodsDetail = goodsDetailDAO.getByGoodsId(id);
        goodsDetailPictureDAO.removeByGoodsDetailId(goodsDetail.getId());
        goodsDetailDAO.remove(goodsDetail.getId());
        goodsPropertyValueDAO.removeByGoodsId(id);

        List<GoodsSkuDO> goodsSkuList = goodsSkuDAO.listByGoodsId(id);
        goodsSkuList.forEach(goodsSku -> {
            goodsSkuSalePropertyValueDAO.removeByGoodsSkuId(goodsSku.getId());
        });
        goodsSkuDAO.removeByGoodsId(id);

        goodsDAO.remove(id);

        return true;
    }
}
