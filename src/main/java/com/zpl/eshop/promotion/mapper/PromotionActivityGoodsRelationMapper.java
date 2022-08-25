package com.zpl.eshop.promotion.mapper;

import com.zpl.eshop.promotion.domain.PromotionActivityGoodsRelationDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 促销活动商品关联关系Mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/8/21 18:20
 **/
@Mapper
public interface PromotionActivityGoodsRelationMapper {

    /**
     * 根据促销活动id查询关联关系
     *
     * @param activityId 促销活动id
     * @return 关系
     */
    @Select("SELECT " +
            "id," +
            "promotion_activity_id, " +
            "goods_id, " +
            "gmt_create, " +
            "gmt_modified " +
            "FROM promotion_activity_goods_relation " +
            "WHERE promotion_activity_id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "promotion_activity_id", property = "promotionActivityId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PromotionActivityGoodsRelationDO> listByActivityId(@Param("activityId") Long activityId);

    /**
     * 新增促销活动与商品关联关系
     *
     * @param relation 关联关系
     */
    @Insert("INSERT INTO promotion_activity_goods_relation " +
            "(" +
            "promotion_activity_id, " +
            "goods_id, " +
            "gmt_create, " +
            "gmt_modified" +
            ") values (" +
            "#{promotionActivityId}," +
            "#{goodsId}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(PromotionActivityGoodsRelationDO relation);

    /**
     * 根据促销活动id删除
     *
     * @param promotionActivityId 促销活动id
     */
    @Delete("DELETE FROM promotion_activity_goods_relation WHERE promotion_activity_id = #{promotionActivityId}")
    void removeByActivityId(@Param("promotionActivityId") Long promotionActivityId);
}
