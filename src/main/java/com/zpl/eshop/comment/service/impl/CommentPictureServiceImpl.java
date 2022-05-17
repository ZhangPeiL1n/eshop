package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.CommentPictureUploadDirType;
import com.zpl.eshop.comment.dao.CommentPictureDAO;
import com.zpl.eshop.comment.domain.CommentPictureDO;
import com.zpl.eshop.comment.domain.CommentPictureDTO;
import com.zpl.eshop.comment.service.CommentPictureService;
import com.zpl.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 评论晒图管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 22:27
 **/
@Service
public class CommentPictureServiceImpl implements CommentPictureService {

    private final Logger logger = LoggerFactory.getLogger(CommentPictureServiceImpl.class);
    /**
     * 评论晒图管理模块DAO组件
     */
    @Autowired
    private CommentPictureDAO commentPictureDAO;

    /**
     * 评论晒图上传目录
     */
    @Value("${comment.picture.upload.dir}")
    private String uploadDirPath;

    @Value("${comment.picture.upload.dir.type}")
    private String uploadDirType;

    @Override
    public Boolean saveCommentPictures(String appBasePath, Long commentInfoId, MultipartFile[] files) {
        if (CommentPictureUploadDirType.RELATIVE.equals(uploadDirType)) {
            uploadDirPath = appBasePath + uploadDirPath;
        }

        try {
            File uploadDir = new File(uploadDirPath);
            // 如果上传目录不存在，则创建目录
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            for (MultipartFile file : files) {
                if (file == null) {
                    continue;
                }

                String targetFilePath = uploadDirPath + file.getOriginalFilename();
                File targetFile = new File(targetFilePath);
                // 如果目标文件已存在，则删除
                if (targetFile.exists()) {
                    targetFile.delete();
                }
                // 将文件上传到指定目录中去
                file.transferTo(targetFile);

                // 将评论晒图信息保存到数据库中去
                CommentPictureDO commentPictureDO = new CommentPictureDO();
                commentPictureDO.setCommentInfoId(commentInfoId);
                commentPictureDO.setCommentPicturePath(targetFilePath);
                commentPictureDO.setGmtCreate(new Date());
                commentPictureDO.setGmtModified(new Date());

                commentPictureDAO.saveCommentPicture(commentPictureDO);
            }
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }

        return true;
    }

    /**
     * 根据评论id获取评论晒图
     *
     * @param commentId 评论id
     * @return 评论晒图
     */
    public List<CommentPictureDTO> listByCommentId(Long commentId) {
        try {
            List<CommentPictureDO> commentPictures = commentPictureDAO.listByCommentId(commentId);
            return ObjectUtils.convertList(commentPictures, CommentPictureDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据图片id获取评论图片
     *
     * @param id id
     * @return 评论图片
     */
    public CommentPictureDTO getById(Long id) {
        try {
            return commentPictureDAO.getById(id).clone(CommentPictureDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }
}
