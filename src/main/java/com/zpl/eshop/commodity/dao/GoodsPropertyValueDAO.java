package com.zpl.eshop.commodity.dao;

import com.zpl.eshop.commodity.domain.GoodsPropertyValueDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性值管理DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/8/27 11:27
 **/
public interface GoodsPropertyValueDAO {

    /**
     * 根据商品id查询属性值
     *
     * @param goodsId 商品id
     * @return 属性值
     */
    List<GoodsPropertyValueDO> listByGoodsId(Long goodsId);

    /**
     * 新增商品属性值
     *
     * @param goodsPropertyValue 商品属性值
     */
    void save(GoodsPropertyValueDO goodsPropertyValue);

    /**
     * 根据商品id删除图片
     *
     * @param goodsId 商品id
     */
    void removeByGoodsId(@Param("goodsId") Long goodsId);

}
