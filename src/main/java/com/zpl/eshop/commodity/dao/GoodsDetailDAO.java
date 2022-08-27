package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsDetailDO;

/**
 * 商品详情管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:16
 **/
public interface GoodsDetailDAO {

    /**
     * 新增商品详情
     *
     * @param detail 商品详情
     */
    Long save(GoodsDetailDO detail);

    /**
     * 更新商品详情
     *
     * @param detail 商品详情
     */
    void update(GoodsDetailDO detail);

}
