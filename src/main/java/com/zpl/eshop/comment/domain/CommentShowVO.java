package com.zpl.eshop.comment.domain;

import lombok.Data;

import java.util.List;

/**
 * 评论前台展示VO
 *
 * @author ZhangPeiL1n
 * @date 2022/8/7 17:22
 **/
@Data
public class CommentShowVO {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 评论统计信息
     */
    private CommentAggregateVO aggregate;


    /**
     * 评论列表
     */
    private List<CommentInfoVO> comments;
}
