package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.mapper.CommentInfoMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 评论信息管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:33
 **/
@Repository
public class CommentInfoDAOImpl implements CommentInfoDAO {

    /**
     * 评论信息管理模块的mapper组件
     */
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfo) throws Exception {
        commentInfo.setGmtCreate(dateProvider.getCurrentTime());
        commentInfo.setGmtModified(dateProvider.getCurrentTime());
        commentInfoMapper.saveCommentInfo(commentInfo);
        return commentInfo.getId();
    }
}
