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
     * @throws Exception
     */
    GoodsDetailDO getByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增商品详情
     *
     * @param detail 商品详情
     * @return id
     * @throws Exception
     */
    Long save(GoodsDetailDO detail) throws Exception;

    /**
     * 更新商品详情
     *
     * @param detail 商品详情
     * @throws Exception
     */
    void update(GoodsDetailDO detail) throws Exception;

    /**
     * 删除商品详情
     *
     * @param id 商品详情id
     * @throws Exception
     */
    void remove(Long id) throws Exception;

}
