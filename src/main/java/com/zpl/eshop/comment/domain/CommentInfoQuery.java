package com.zpl.eshop.comment.domain;

import com.zpl.eshop.common.util.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论信息的查询条件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/20 21:46
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class CommentInfoQuery extends AbstractObject {

    /**
     * 分页查询起始位置
     */
    private Integer offsite;

    /**
     * 分页查询数
     */
    private Integer size;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 总评分
     */
    private Integer totalScore;

    /**
     * 评论状态
     */
    private Integer commentStatus;

    /**
     * 评论类型
     */
    private Integer commentType;

    /**
     * 是否晒图
     */
    private Integer showPictures;

    /**
     * 是否默认评论
     */
    private Integer defaultComment;

}
