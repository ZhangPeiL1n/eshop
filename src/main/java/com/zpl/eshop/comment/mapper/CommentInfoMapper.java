package com.zpl.eshop.comment.mapper;

import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 评论信息管理模块的mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:14
 **/
@Mapper
public interface CommentInfoMapper {

    /**
     * 新增评论信息
     *
     * @param commentInfoDO 评论信息对象
     */
    @Insert("INSERT INTO comment_info(" +
            "user_account_id," +
            "username," +
            "order_info_id," +
            "order_item_id," +
            "goods_id," +
            "goods_sku_id," +
            "goods_sku_sale_properties," +
            "total_score," +
            "goods_score," +
            "customer_service_score," +
            "logistics_score," +
            "comment_content," +
            "is_show_pictures," +
            "is_default_comment," +
            "comment_status," +
            "comment_type," +
            "gmt_create," +
            "gmt_modified" +
            ")" +
            "VALUES(" +
            "#{userAccountId}," +
            "#{username}," +
            "#{orderInfoId}," +
            "#{orderItemId}," +
            "#{goodsId}," +
            "#{goodsSkuId}," +
            "#{goodsSkuSaleProperties}," +
            "#{totalScore}," +
            "#{goodsScore}," +
            "#{customerServiceScore}," +
            "#{logisticsScore}," +
            "#{commentContent}," +
            "#{showPicture}," +
            "#{defaultComment}," +
            "#{commentStatus}," +
            "#{commentType}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveCommentInfo(CommentInfoDO commentInfoDO);

    /**
     * 分页列表查询
     *
     * @param query 分页查询条件
     * @return 评论信息
     */
    @Select("<script>" +
            "SELECT " +
            "a.id," +
            "a.user_account_id," +
            "a.username," +
            "a.order_info_id," +
            "a.order_item_id," +
            "a.goods_id," +
            "a.goods_sku_id," +
            "a.goods_sku_sale_properties," +
            "a.total_score," +
            "a.goods_score," +
            "a.customer_service_score," +
            "a.logistics_score," +
            "a.is_show_pictures," +
            "a.is_default_comment," +
            "a.comment_status," +
            "a.comment_type," +
            "a.gmt_create," +
            "a.gmt_modified " +
            "FROM comment_info a," +
            "(SELECT id " +
            "FROM comment_info " +
            "WHERE 1=1 " +
            "<if test='startTime != null'>" +
            "AND gmt_create>=#{startTIme} " +
            "</if>" +
            "<if test='endTime != null'" +
            "AND gmt_create<=#{endTime} " +
            "</if>" +
            "<if test='totalScore != null'" +
            "AND total_score=#{totalScore} " +
            "</if>" +
            "<if test='commentStatus != null'" +
            "AND comment_status=#{commentStatus} " +
            "</if>" +
            "<if test='commentType != null'" +
            "AND comment_type=#{commentType} " +
            "</if>" +
            "<if test='showPictures != null'" +
            "AND show_pictures=#{showPictures} " +
            "</if>" +
            "<if test='defaultComment != null'" +
            "AND is_default_comment=#{defaultComment} " +
            "</if>" +
            "limit #{offsite},#{size}" +
            ") b " +
            "WHERE a.id = b.id" +
            "</scripty>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_account_id", property = "userAccount_id"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "order_Item_id", property = "orderItemId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "goods_sku_sale_properties", property = "goodsSkuSaleProperties"),
            @Result(column = "total_score", property = "totalScore"),
            @Result(column = "goods_score", property = "goodsScore"),
            @Result(column = "custom_service_score", property = "customServiceScore"),
            @Result(column = "logistics_score", property = "logisticsScore"),
            @Result(column = "comment_content", property = "commentContent"),
            @Result(column = "is_show_pictures", property = "isShowPictures"),
            @Result(column = "is_default_comment", property = "isDefaultComment"),
            @Result(column = "comment_status", property = "commentStatus"),
            @Result(column = "comment_type", property = "commentType"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CommentInfoDO> listByPage(CommentInfoQuery query);


    /**
     * 根据id获取评论详情
     *
     * @param id 评论id
     * @return 评论详情
     */
    @Select("select " +
            "id," +
            "user_account_id," +
            "username," +
            "order_info_id," +
            "order_item_id," +
            "goods_id," +
            "goods_sku_id," +
            "goods_sku_sale_properties," +
            "total_score," +
            "goods_score," +
            "customer_service_score," +
            "logistics_score," +
            "is_show_pictures," +
            "is_default_comment," +
            "comment_status," +
            "comment_type," +
            "gmt_create," +
            "gmt_modified " +
            "from comment_info " +
            "where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "user_account_id", property = "userAccount_id"),
            @Result(column = "username", property = "username"),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "order_Item_id", property = "orderItemId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_sku_id", property = "goodsSkuId"),
            @Result(column = "goods_sku_sale_properties", property = "goodsSkuSaleProperties"),
            @Result(column = "total_score", property = "totalScore"),
            @Result(column = "goods_score", property = "goodsScore"),
            @Result(column = "custom_service_score", property = "customServiceScore"),
            @Result(column = "logistics_score", property = "logisticsScore"),
            @Result(column = "comment_content", property = "commentContent"),
            @Result(column = "is_show_pictures", property = "isShowPictures"),
            @Result(column = "is_default_comment", property = "isDefaultComment"),
            @Result(column = "comment_status", property = "commentStatus"),
            @Result(column = "comment_type", property = "commentType"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    CommentInfoDO getById(@Param("id") Long id);

    /**
     * 更新评论
     *
     * @param comment 评论信息
     */
    @Update("update comment_info set " +
            "comment_status = #{commentStatus}," +
            "gmt_modified = #{gmtModified} " +
            "where id = #{id}")
    void update(CommentInfoDO comment);

    /**
     * 删除评论
     *
     * @param id id
     */
    @Delete("delete from comment_info " +
            "where id = #{id}")
    void delete(Long id);
}
