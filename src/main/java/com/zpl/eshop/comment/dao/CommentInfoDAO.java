package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentInfoDO;

/**
 * 评论管理模块DAO接口组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:32
 **/
public interface CommentInfoDAO {

    /**
     * 新增评论信息
     *
     * @param commentInfo 评论信息对象
     * @return 新增是否成功
     * @throws Exception
     */
    Long saveCommentInfo(CommentInfoDO commentInfo) throws Exception;
}
