package com.zpl.eshop.commodity.mapper;

import com.zpl.eshop.commodity.domain.GoodsDO;
import com.zpl.eshop.commodity.domain.GoodsQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 查询关联品牌的商品总数
     *
     * @param brandId 品牌id
     * @return 商品数量
     */
    @Select("select count(*) from commodity_goods where brand_id = #{brandId}")
    Long countByBrandId(@Param("brandId") Long brandId);


    /**
     * 分页查询商品
     *
     * @param query 查询条件
     * @return 商品集合
     */
    @Select("<script>" +
            "SELECT " +
            "a.id," +
            "a.category_id," +
            "a.brand_id," +
            "a.code," +
            "a.name," +
            "a.sub_name," +
            "a.gross_weight," +
            "a.length," +
            "a.width," +
            "a.height," +
            "a.status," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "FROM commodity_goods a," +
            "(" +
            "SELECT id " +
            "FROM commodity_goods " +
            "WHERE 1=1 " +
            "<if test='categoryId != null'>" +
            "AND category_id = #{categoryId} " +
            "</if>" +
            "<if test='brandId != null'>" +
            "AND brand_id = #{brandId} " +
            "</if>" +
            "<if test='code != null'>" +
            "AND code liek '${code}%' " +
            "</if>" +
            "<if test='name != null'>" +
            "AND name like '${name}%' " +
            "</if>" +
            "<if test='subName != null'>" +
            "AND subName like '${subName}%' " +
            "</if>" +
            "<if test='status != null'>" +
            "AND status = '${status}%' " +
            "</if>" +
            "limit #{offset},#{size}" +
            ") b " +
            "WHERE a.id = b.id " +
            "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "brand_id", property = "brandId"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "sub_name", property = "subName"),
            @Result(column = "gross_weight", property = "grossWeight"),
            @Result(column = "length", property = "length"),
            @Result(column = "width", property = "width"),
            @Result(column = "height", property = "height"),
            @Result(column = "status", property = "status"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<GoodsDO> listByPage(GoodsQuery query);

    /**
     * 新增商品
     *
     * @param goods 商品
     */
    @Insert("INSERT INTO commodity_goods (" +
            "category_id, " +
            "brand_id," +
            "code," +
            "name, " +
            "sub_name," +
            "gross_weight," +
            "length," +
            "width," +
            "height," +
            "status," +
            "service_guarantees," +
            "package_list," +
            "freight_template_id," +
            "gmt_create," +
            "gmt_modified" +
            ") VALUES (" +
            "#{categoryId}," +
            "#{brandId}," +
            "#{code}," +
            "#{name}," +
            "#{subName}," +
            "#{grossWeight}," +
            "#{length}," +
            "#{width}," +
            "#{height}," +
            "#{status}," +
            "#{serviceGuarantees}," +
            "#{packageList}," +
            "#{freightTemplateId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(GoodsDO goods);
}
