package com.zpl.eshop.comment.dao.impl;

import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import com.zpl.eshop.comment.mapper.CommentInfoMapper;
import com.zpl.eshop.common.util.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论信息管理模块的DAO组件
 *
 * @author ZhangPeiL1n
 */
@Repository
public class CommentInfoDAOImpl implements CommentInfoDAO {

    /**
     * 评论信息管理模块的mapper组件
     */
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    /**
     * 新增评论信息
     *
     * @param commentInfoDO 评论信息DO对象
     */
    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfoDO) {
        commentInfoMapper.saveCommentInfo(commentInfoDO);
        return commentInfoDO.getId();
    }

    /**
     * 分页查询评论信息
     *
     * @param query 评论查询条件
     * @return 评论信息
     */
    @Override
    public List<CommentInfoDO> listByPage(CommentInfoQuery query) {
        return commentInfoMapper.listByPage(query);
    }

    /**
     * 根据id查询评论信息
     *
     * @param id 评论信息id
     * @return 评论信息
     */
    @Override
    public CommentInfoDO getById(Long id) {
        return commentInfoMapper.getById(id);
    }

    /**
     * 更新评论
     *
     * @param comment 评论信息
     */
    @Override
    public Boolean update(CommentInfoDO comment) {
        commentInfoMapper.update(comment);
        return true;
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     */
    @Override
    public Boolean remove(Long id) {
        commentInfoMapper.remove(id);
        return true;
    }

}
