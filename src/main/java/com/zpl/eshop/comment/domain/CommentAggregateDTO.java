package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论聚合统计信息
 *
 * @author ZhangPeiL1n
 * @date 2022/1/11 22:29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentAggregateDTO extends AbstractObject {

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
    private Long showPicturesCommentCount;

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
