package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentPictureDAO;
import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.comment.mapper.CommentPictureMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    @Override
    public void saveCommentPicture(CommentPictureDO commentPicture) throws Exception {
        commentPicture.setGmtCreate(dateProvider.getCurrentTime());
        commentPicture.setGmtModified(dateProvider.getCurrentTime());
        mapper.saveCommentPicture(commentPicture);
    }
}
