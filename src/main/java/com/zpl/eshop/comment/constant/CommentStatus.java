package com.zpl.eshop.comment.constant;

/**
 * 评论状态的常量类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/13 21:42
 **/
public class CommentStatus {

    /**
     * 待审核
     */
    public static final Integer APPROVING = 1;

    /**
     * 审核通过
     */
    public static final Integer APPROVED = 2;

    /**
     * 审核未通过
     */
    public static final Integer APPROVE_REJECT = 3;

    private CommentStatus() {
    }
}
