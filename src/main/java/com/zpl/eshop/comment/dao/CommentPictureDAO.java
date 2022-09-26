package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentPictureDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论晒图管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:19
 **/
public interface CommentPictureDAO {


    /**
     * 新增评论晒图
     *
     * @param commentPictureDO 评论晒图DO对象
     * @return 处理结果
     */
    Long saveCommentPicture(CommentPictureDO commentPictureDO);

    /**
     * 根据评论id获取评论晒图
     *
     * @param commentId 评论id
     * @return 评论晒图
     */
    List<CommentPictureDO> listByCommentId(@Param("commentId") Long commentId);


    /**
     * 根据图片id获取评论图片
     *
     * @param id id
     * @return 评论图片
     */
    CommentPictureDO getById(Long id);
}
