package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 评论晒图
 *
 * @author ZhangPeiL1n
 * @date 2022/1/11 22:26
 **/
@Getter
@Setter
@ToString
public class CommentPictureDO extends AbstractObject {
    /**
     * id
     */
    private Long id;

    /**
     * 评论id
     */
    private Long commentInfoId;

    /**
     * 评论晒图路径
     */
    private String commentPicturePath;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
