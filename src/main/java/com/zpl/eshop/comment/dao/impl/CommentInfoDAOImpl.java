package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.mapper.CommentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(CommentInfoDAOImpl.class);

    @Override
    public Boolean saveCommentInfo(CommentInfoDO commentInfoDO) {
        try {
            commentInfoMapper.saveCommentInfo(commentInfoDO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }
}
