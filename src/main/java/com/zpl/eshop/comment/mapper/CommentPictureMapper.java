package com.zpl.eshop.comment.mapper;

import com.zpl.eshop.comment.domain.CommentPictureDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 评论晒图管理模块的mapper组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:12
 **/
@Mapper
public interface CommentPictureMapper {

    /**
     * 新增评论晒图
     *
     * @param commentPictureDO 评论晒图DO对象
     */
    @Insert("INSERT INTO comment_picture(" +
            "comment_info_id," +
            "comment_picture_path," +
            "gmt_create," +
            "gmt_modified" +
            ")VALUES(" +
            "#{commentInfoId}," +
            "#{commentPicturePath}," +
            "#{gmtCreate}," +
            "#{gmtModified}" +
            ")")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void saveCommentPicture(CommentPictureDO commentPictureDO);

    /**
     * 根据评论id获取评论晒图
     *
     * @param commentId 评论id
     * @return 评论晒图
     */
    @Select("select " +
            "id," +
            "comment_info_id," +
            "comment_picture_path," +
            "gmt_creat," +
            "gmt_modified " +
            "from comment_picuture " +
            "where comment_info_id = #{commentId}}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "comment_info_id", property = "commentInfoId"),
            @Result(column = "comment_picture_path", property = "commentPicturePath"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<CommentPictureDO> listByCommentId(@Param("commentId") Long commentId);

    /**
     * 根据图片id获取评论图片
     *
     * @param id id
     * @return 评论图片
     */
    @Select("select " +
            "id," +
            "comment_info_id," +
            "comment_picture_path," +
            "gmt_creat," +
            "gmt_modified " +
            "from comment_picuture " +
            "where id = #{id}}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "comment_info_id", property = "commentInfoId"),
            @Result(column = "comment_picture_path", property = "commentPicturePath"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    CommentPictureDO getById(@Param("id") Long id);
}
