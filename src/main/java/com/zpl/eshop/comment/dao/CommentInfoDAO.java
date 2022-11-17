package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;

import java.util.List;

/**
 * 评论信息管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 */
public interface CommentInfoDAO {

    /**
     * 新增评论信息
     *
     * @param commentInfo 评论信息DO对象
     * @return id
     * @throws Exception
     */
    Long saveCommentInfo(CommentInfoDO commentInfo) throws Exception;

    /**
     * 分页查询评论信息
     *
     * @param query 评论查询条件
     * @return 评论信息
     * @throws Exception
     */
    List<CommentInfoDO> listByPage(CommentInfoQuery query) throws Exception;

    /**
     * 根据id查询评论信息
     *
     * @param id 评论信息id
     * @return 评论信息
     * @throws Exception
     */
    CommentInfoDO getById(Long id) throws Exception;

    /**
     * 更新评论
     *
     * @param comment 评论信息
     * @return 更新结果
     * @throws Exception
     */
    Boolean update(CommentInfoDO comment) throws Exception;

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return 删除结果
     * @throws Exception
     */
    Boolean remove(Long id) throws Exception;

}
