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
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 新增评论信息
     *
     * @param commentInfo 评论信息DO对象
     * @throws Exception
     */
    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfo) throws Exception {
        commentInfo.setGmtCreate(dateProvider.getCurrentTime());
        commentInfo.setGmtModified(dateProvider.getCurrentTime());
        commentInfoMapper.saveCommentInfo(commentInfo);
        return commentInfo.getId();
    }

    /**
     * 分页查询评论信息
     *
     * @param query 评论查询条件
     * @return 评论信息
     * @throws Exception
     */
    @Override
    public List<CommentInfoDO> listByPage(CommentInfoQuery query) throws Exception {
        return commentInfoMapper.listByPage(query);
    }

    /**
     * 根据id查询评论信息
     *
     * @param id 评论信息id
     * @return 评论信息
     * @throws Exception
     */
    @Override
    public CommentInfoDO getById(Long id) throws Exception {
        return commentInfoMapper.getById(id);
    }

    /**
     * 更新评论
     *
     * @param comment 评论信息
     * @throws Exception
     */
    @Override
    public Boolean update(CommentInfoDO comment) throws Exception {
        comment.setGmtModified(dateProvider.getCurrentTime());
        commentInfoMapper.update(comment);
        return true;
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @throws Exception
     */
    @Override
    public Boolean remove(Long id) throws Exception {
        commentInfoMapper.remove(id);
        return true;
    }
}
