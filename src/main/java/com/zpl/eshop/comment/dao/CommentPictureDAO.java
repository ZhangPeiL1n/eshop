package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentPictureDO;

import java.util.List;

/**
 * 评论晒图管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface CommentPictureDAO {

    /**
     * 新增评论晒图
     *
     * @param commentPicture 评论晒图DO对象
     * @return id
     * @throws Exception
     */
    Long saveCommentPicture(CommentPictureDO commentPicture) throws Exception;

    /**
     * 根据评论信息id查询图片
     *
     * @param commentId 评论信息id
     * @return 评论图片
     * @throws Exception
     */
    List<CommentPictureDO> listByCommentId(Long commentId) throws Exception;

    /**
     * 根据id查询图片
     *
     * @param id 评论图片id
     * @return 评论图片
     * @throws Exception
     */
    CommentPictureDO getById(Long id) throws Exception;
}
