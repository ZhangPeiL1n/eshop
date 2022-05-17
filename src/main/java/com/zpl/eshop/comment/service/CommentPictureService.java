package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.domain.CommentPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 评论晒图管理模块Service组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:25
 **/
public interface CommentPictureService {


    /**
     * 新增评论晒图
     *
     * @param appBasePath   当前应用根路径
     * @param commentInfoId 评论信息id
     * @param files         文件
     * @return 处理结果
     */
    Boolean saveCommentPictures(String appBasePath, Long commentInfoId, MultipartFile[] files);

    /**
     * 根据评论id获取评论晒图
     *
     * @param commentId 评论id
     * @return 评论晒图
     */
    List<CommentPictureDTO> listByCommentId(Long commentId);

    /**
     * 根据图片id获取评论图片
     *
     * @param id id
     * @return 评论图片
     */
    CommentPictureDTO getById(Long id);
}
