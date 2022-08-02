package com.zpl.eshop.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商品管理Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 23:04
 **/
@Mapper
public interface GoodsMapper {

    /**
     * 查询关联类目的商品总数
     *
     * @param categoryId 类目id
     * @return 商品数量
     */
    @Select("select count(*) from commodity_goods where category_id = #{categoryId}")
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}
