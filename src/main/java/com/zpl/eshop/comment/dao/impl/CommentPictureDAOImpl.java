package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentPictureDAO;
import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.comment.mapper.CommentPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论晒图管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class CommentPictureDAOImpl implements CommentPictureDAO {

    /**
     * 评论晒图管理模块的mapper组件
     */
    @Autowired
    private CommentPictureMapper commentPictureMapper;

    /**
     * 新增评论晒图
     *
     * @param commentPictureDO 评论晒图DO对象
     */
    @Override
    public Long saveCommentPicture(CommentPictureDO commentPictureDO) {
        commentPictureMapper.saveCommentPicture(commentPictureDO);
        return commentPictureDO.getId();
    }

    /**
     * 根据评论信息id查询图片
     *
     * @param commentId 评论信息id
     * @return 评论图片
     */
    @Override
    public List<CommentPictureDO> listByCommentId(Long commentId) {
        return commentPictureMapper.listByCommentId(commentId);
    }

    /**
     * 根据id查询图片
     *
     * @param id 评论图片id
     * @return 评论图片
     */
    @Override
    public CommentPictureDO getById(Long id) {
        return commentPictureMapper.getById(id);
    }

}
