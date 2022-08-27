package com.zpl.eshop.commodity.service.impl;

import com.zpl.eshop.commodity.dao.GoodsDAO;
import com.zpl.eshop.commodity.domain.GoodsDO;
import com.zpl.eshop.commodity.domain.GoodsDTO;
import com.zpl.eshop.commodity.domain.GoodsQuery;
import com.zpl.eshop.commodity.service.GoodsService;
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
     * 新增商品
     *
     * @param goods 商品
     */
    @Override
    public Long save(GoodsDTO goods) throws Exception {
        goodsDAO.save(goods.clone(GoodsDO.class));
        return goods.getId();
    }
}
