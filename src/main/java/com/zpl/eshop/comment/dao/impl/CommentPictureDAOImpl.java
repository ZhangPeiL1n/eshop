package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentPictureDAO;
import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.comment.mapper.CommentPictureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论晒图管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:21
 **/
@Repository
public class CommentPictureDAOImpl implements CommentPictureDAO {

    /**
     * 评论晒图管理模块的mapper组件
     */
    @Autowired
    private CommentPictureMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(CommentPictureDAOImpl.class);

    @Override
    public Long saveCommentPicture(CommentPictureDO commentPictureDO) {
        try {
            mapper.saveCommentPicture(commentPictureDO);
            return commentPictureDO.getId();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据评论id获取评论晒图
     *
     * @param commentId 评论id
     * @return 评论晒图
     */
    public List<CommentPictureDO> listByCommentId(Long commentId) {
        try {
            return mapper.listByCommentId(commentId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据图片id获取评论图片
     *
     * @param id id
     * @return 评论图片
     */
    public CommentPictureDO getById(Long id) {
        try {
            return mapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
