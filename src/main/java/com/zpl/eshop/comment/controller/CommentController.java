package com.zpl.eshop.comment.controller;

import com.zpl.eshop.comment.constant.ShowPicture;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.domain.CommentInfoVO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.comment.service.CommentPictureService;
import com.zpl.eshop.membership.service.MembershipFacadeService;
import com.zpl.eshop.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论管理模块Controller组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/12 22:06
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


    /**
     * 评论信息管理模块Service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;

    /**
     * 评论晒图管理模块Service组件
     */
    @Autowired
    private CommentPictureService commentPictureService;

    /**
     * 评论统计信息管理模块Service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 订单中心的Service组件
     */
    @Autowired
    private OrderService orderService;

    /**
     * 会员中心的Service组件
     */
    @Autowired
    private MembershipFacadeService membershipFacadeService;

    /**
     * 手动发表评论
     *
     * @param commentInfoVO 评论信息VO
     * @return 处理结果
     */
    @PostMapping("/")
    public Boolean publishComment(HttpServletRequest request, CommentInfoVO commentInfoVO, MultipartFile[] files) {
        try {
            commentInfoVO.setShowPicture(ShowPicture.NO);
            // 设置是否晒图
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (file != null) {
                        commentInfoVO.setShowPicture(ShowPicture.YES);
                        break;
                    }
                }
            }

            // 保存评论
            CommentInfoDTO commentInfoDTO = commentInfoVO.clone(CommentInfoDTO.class);
            commentInfoService.saveManualPublishedCommentInfo(commentInfoDTO);

            // 上传评论晒图图片
            String appBasePath = request.getSession().getServletContext().getRealPath("/");
            commentPictureService.saveCommentPictures(appBasePath, commentInfoDTO.getId(), files);

            // 更新评论统计信息
            commentAggregateService.updateCommentAggregate(commentInfoDTO);

            // 这里就用门面模式交互了
            // 通知订单中心，发表评论事件发生了
            orderService.informPublishCommentEvent(commentInfoDTO.getOrderInfoId());

            // 通知用户中心，用户已经发表评论
            membershipFacadeService.informPublishCommentEvent(commentInfoDTO.getUserAccountId(), ShowPicture.YES.equals(commentInfoDTO.getCommentType()));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

}
