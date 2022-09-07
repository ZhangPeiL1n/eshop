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
     * 根据商品id查询商品详情
     *
     * @param goodsId 商品id
     * @return 商品详情
     */
    GoodsDetailDO getByGoodsId(Long goodsId);

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

    /**
     * 删除商品详情
     *
     * @param id 商品详情id
     */
    void remove(Long id);

}
