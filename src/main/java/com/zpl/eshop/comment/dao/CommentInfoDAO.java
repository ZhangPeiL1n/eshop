package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论信息管理模块DAO接口组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:32
 **/
public interface CommentInfoDAO {
    /**
     * 新增评论信息
     *
     * @param commentInfoDO 评论信息对象
     * @return 新增是否成功
     */
    Long saveCommentInfo(CommentInfoDO commentInfoDO);


    /**
     * 分页列表查询
     *
     * @param query 分页查询条件
     * @return
     */
    List<CommentInfoDO> listByPage(CommentInfoQuery query);

    /**
     * 根据id获取评论详情
     *
     * @param id 评论id
     * @return 评论详情
     */
    CommentInfoDO getById(@Param("id") Long id);


    /**
     * 更新评论
     *
     * @param comment 评论信息
     * @return 是否成功
     */
    Boolean update(CommentInfoDO comment);

    /**
     * 删除评论
     *
     * @param id id
     * @return 是否成功
     */
    Boolean delete(Long id);
}
