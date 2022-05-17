package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import com.zpl.eshop.comment.mapper.CommentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    /**
     * 新增评论信息
     *
     * @param commentInfoDO 评论信息对象
     * @return 新增是否成功
     */
    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfoDO) {
        try {
            commentInfoMapper.saveCommentInfo(commentInfoDO);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return commentInfoDO.getId();
    }

    /**
     * 分页列表查询
     *
     * @param query 分页查询条件
     * @return 评论信息
     */
    public List<CommentInfoDO> listByPage(CommentInfoQuery query) {
        try {
            return commentInfoMapper.listByPage(query);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id获取评论详情
     *
     * @param id 评论id
     * @return 评论详情
     */
    public CommentInfoDO getById(Long id) {
        try {
            return commentInfoMapper.getById(id);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
