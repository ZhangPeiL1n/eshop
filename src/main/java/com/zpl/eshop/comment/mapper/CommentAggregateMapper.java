package com.zpl.eshop.comment.mapper;

import com.zpl.eshop.comment.domain.CommentAggregateDO;
import org.apache.ibatis.annotations.*;

/**
 * 评论统计信息管理模块的 Mapper
 *
 * @author ZhangPeiL1n
 * @date 2022/1/17 22:21
 **/
@Mapper
public interface CommentAggregateMapper {

    /**
     * 根据商品 id 查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    @Select("SELECT " +
            "id," +
            "goods_id," +
            "total_comment_count," +
            "good_comment_count," +
            "good_comment_rate," +
            "show_pictures_comment_count," +
            "medium_comment_count," +
            "bad_comment_count," +
            "gmt_create," +
            "gmt_modified " +
            "FROM comment_aggregate " +
            "WHERE goods_id = #{goodsId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "total_comment_count", property = "totalCommentCount"),
            @Result(column = "good_comment_count", property = "goodCommentCount"),
            @Result(column = "good_comment_rate", property = "goodCommentRate"),
            @Result(column = "show_pictures_comment_count", property = "showPicturesCommentCount"),
            @Result(column = "medium_comment_count", property = "mediumCommentCount"),
            @Result(column = "bad_comment_count", property = "badCommentCount"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    CommentAggregateDO getCommentAggregateByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 新增评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO
     */
    @Insert("INSERT INTO comment_aggregate(" +
            "goods_id," +
            "total_comment_count," +
            "good_comment_count," +
            "good_comment_rate," +
            "show_pictures_comment_count," +
            "medium_comment_count," +
            "bad_comment_count," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{goodsId}," +
            "#{totalCommentCount}," +
            "#{goodCommentCount}," +
            "#{goodCommentRate}," +
            "#{showPicturesCommentCount}," +
            "#{mediumCommentCount}," +
            "#{badCommentCount}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void saveCommentAggregate(CommentAggregateDO commentAggregateDO);

    /**
     * 更新评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO
     */
    @Update("UPDATE comment_aggregate SET " +
            "goods_id=#{goodsId}," +
            "total_comment_count=#{totalCommentCount}," +
            "good_comment_count=#{goodCommentCount}," +
            "good_comment_rate=#{goodCommentRate}," +
            "show_pictures_comment_count=#{showPicturesCommentCount}," +
            "medium_comment_count=#{mediumCommentCount}," +
            "bad_comment_count=#{badCommentCount}," +
            "gmt_create=#{gmtCreate}," +
            "gmt_modified=#{gmtModified} " +
            "WHERE id=#{id}")
    void updateCommentAggregate(CommentAggregateDO commentAggregateDO);
}
