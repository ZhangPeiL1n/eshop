package com.zpl.eshop.comment.mapper;

import com.zpl.eshop.comment.domain.CommentPictureDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

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
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void saveCommentPicture(CommentPictureDO commentPictureDO);
}
