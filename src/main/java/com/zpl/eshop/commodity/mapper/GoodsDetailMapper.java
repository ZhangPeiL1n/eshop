package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsDetailDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * 商品详情页Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/26 22:06
 **/
@Mapper
public interface GoodsDetailMapper {

    /**
     * 新增商品详情
     *
     * @param detail 商品详情
     */
    @Insert("INSERT INTO commodity_goods_detail(" +
            "goods_id," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{goodsId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(GoodsDetailDO detail);

    /**
     * 更新商品详情
     *
     * @param detail 商品详情
     */
    @Update("UPDATE commodity_goods_detail SET " +
            "detail_content = #{detailContent} " +
            "WHERE id = #{id}")
    void update(GoodsDetailDO detail);
}
