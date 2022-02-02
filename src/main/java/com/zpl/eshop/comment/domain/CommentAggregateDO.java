package com.zpl.eshop.comment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 评论聚合统计信息
 *
 * @author ZhangPeiL1n
 * @date 2022/1/11 22:29
 **/
@Getter
@Setter
@ToString
public class CommentAggregateDO {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 评论总数量
     */
    private Long totalCommentCount;

    /**
     * 好评数量
     */
    private Long goodCommentCount;

    /**
     * 好评率
     */
    private Double goodCommentRate;

    /**
     * 晒图评论数量
     */
    private Long showPictureCommentCount;

    /**
     * 中评数量
     */
    private Long mediumCommentCount;

    /**
     * 差评数量
     */
    private Long badCommentCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
