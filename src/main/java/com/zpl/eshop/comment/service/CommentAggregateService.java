package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentInfoDTO;

/**
 * 评论统计信息管理模块Service接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:50
 **/
public interface CommentAggregateService {

    /**
     * 更新评论统计信息
     *
     * @param commentInfo 评论信息DTO
     * @return 更新是否成功
     * @throws Exception
     */
    Boolean updateCommentAggregate(CommentInfoDTO commentInfo) throws Exception;
}
