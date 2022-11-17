package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentPictureDAO;
import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.comment.mapper.CommentPictureMapper;
import com.zpl.eshop.common.util.DateProvider;
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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增评论晒图
     *
     * @param commentPicture 评论晒图DO对象
     * @throws Exception
     */
    @Override
    public Long saveCommentPicture(CommentPictureDO commentPicture) throws Exception {
        commentPicture.setGmtCreate(dateProvider.getCurrentTime());
        commentPicture.setGmtModified(dateProvider.getCurrentTime());
        commentPictureMapper.saveCommentPicture(commentPicture);
        return commentPicture.getId();
    }

    /**
     * 根据评论信息id查询图片
     *
     * @param commentId 评论信息id
     * @return 评论图片
     * @throws Exception
     */
    @Override
    public List<CommentPictureDO> listByCommentId(Long commentId) throws Exception {
        return commentPictureMapper.listByCommentId(commentId);
    }

    /**
     * 根据id查询图片
     *
     * @param id 评论图片id
     * @return 评论图片
     * @throws Exception
     */
    @Override
    public CommentPictureDO getById(Long id) throws Exception {
        return commentPictureMapper.getById(id);
    }
}
