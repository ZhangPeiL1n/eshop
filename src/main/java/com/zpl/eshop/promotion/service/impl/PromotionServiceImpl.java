package com.zpl.eshop.promotion.service.impl;

import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.promotion.dao.PromotionActivityDAO;
import com.zpl.eshop.promotion.domain.CouponDTO;
import com.zpl.eshop.promotion.domain.PromotionActivityDTO;
import com.zpl.eshop.promotion.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/2/8 22:59
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PromotionServiceImpl implements PromotionService {

    private final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    @Autowired
    private DateProvider dateProvider;

    /**
     * 促销活动管理模块DAO组件
     */
    @Autowired
    private PromotionActivityDAO promotionActivityDAO;

    /**
     * 根据商品 id 查询促销活动
     *
     * @param goodsId 商品 id
     * @return 促销活动
     */
    @Override
    public List<PromotionActivityDTO> listByGoodsId(Long goodsId) throws Exception {
        return ObjectUtils.convertList(promotionActivityDAO.listEnabledByGoodsId(goodsId), PromotionActivityDTO.class);
    }

    /**
     * 根据id查询促销活动
     *
     * @param id 促销活动id
     * @return 促销活动
     */
    @Override
    public PromotionActivityDTO getById(Long id) throws Exception {
        return promotionActivityDAO.getById(id).clone(PromotionActivityDTO.class);
    }

    /**
     * 查询用户当前可以使用的优惠券
     *
     * @param userAccountId 用户帐号id
     * @return 有效优惠券
     */
    @Override
    public List<CouponDTO> listValidByUserAccount(Long userAccountId) {
        return null;
    }
}
