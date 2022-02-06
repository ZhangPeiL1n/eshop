package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.domain.CommentPictureDO;

/**
 * 评论晒图管理模块的DAO组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:19
 **/
public interface CommentPictureDAO {


    /**
     * 新增评论晒图
     *
     * @param commentPictureDO 评论晒图DO对象
     * @return 处理结果
     */
    Boolean saveCommentPicture(CommentPictureDO commentPictureDO);
}
