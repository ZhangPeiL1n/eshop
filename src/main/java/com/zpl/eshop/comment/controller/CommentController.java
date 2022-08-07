package com.zpl.eshop.comment.controller;

import com.zpl.eshop.comment.constant.CommentApproved;
import com.zpl.eshop.comment.constant.CommentStatus;
import com.zpl.eshop.comment.constant.ShowPictures;
import com.zpl.eshop.comment.domain.*;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.comment.service.CommentPictureService;
import com.zpl.eshop.common.util.ObjectUtils;
import com.zpl.eshop.membership.service.MembershipFacadeService;
import com.zpl.eshop.order.service.OrderFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private OrderFacadeService orderFacadeService;

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
            commentInfoVO.setShowPicture(ShowPictures.NO);
            // 设置是否晒图
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (file != null) {
                        commentInfoVO.setShowPicture(ShowPictures.YES);
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
            commentAggregateService.refreshCommentAggregate(commentInfoDTO);

            // 这里就用门面模式交互了
            // 通知订单中心，发表评论事件发生了
            orderFacadeService.informPublishCommentEvent(commentInfoDTO.getOrderInfoId());

            // 通知用户中心，用户已经发表评论
            membershipFacadeService.informPublishCommentEvent(commentInfoDTO.getUserAccountId(), ShowPictures.YES.equals(commentInfoDTO.getCommentType()));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 分页查询评论信息
     *
     * @param query 查询条件
     * @return 评论信息
     */
    @GetMapping("/")
    public List<CommentInfoVO> listByPage(CommentInfoQuery query) {
        try {
            List<CommentInfoDTO> comments = commentInfoService.listByPage(query);
            return ObjectUtils.convertList(comments, CommentInfoVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public CommentInfoVO getById(@PathVariable("id") Long id) {
        try {
            CommentInfoVO vo = commentInfoService.getById(id).clone(CommentInfoVO.class);
            List<CommentPictureDTO> commentPictures = commentPictureService.listByCommentId(id);
            vo.setPictures(ObjectUtils.convertList(commentPictures, CommentPictureVO.class));
            return vo;
        } catch (Exception e) {
            logger.error("error", e);
            return new CommentInfoVO();
        }
    }

    @GetMapping("/picture/{id}")
    public void viewPicture(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            CommentPictureDTO picture = commentPictureService.getById(id);
            File file = new File(picture.getCommentPicturePath());
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            response.setContentType("image/jpg");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("error", e);
                }
            }
        }
    }

    /**
     * 审核评论
     *
     * @param id       评论id
     * @param approved 是否通过
     */
    @PutMapping("/approve/{id}")
    public Boolean update(@PathVariable("id") Long id, Integer approved) {
        try {
            CommentInfoDTO targetComment = new CommentInfoDTO();
            targetComment.setId(id);
            targetComment.setCommentStatus(CommentApproved.YES.equals(approved) ? CommentStatus.APPROVED : CommentStatus.APPROVE_REJECT);
            return commentInfoService.update(targetComment);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        try {
            return commentInfoService.delete(id);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }


    /**
     * 在前台展示评论信息
     *
     * @param goodsId 商品id
     * @return
     */
    @GetMapping("/show/#{goodsId}")
    public CommentShowVO show(@PathVariable("goodsId") Long goodsId, CommentInfoQuery commentInfoQuery) {
        try {
            // 构造评论展示的VO对象
            CommentShowVO commentShow = new CommentShowVO();
            commentShow.setGoodsId(goodsId);
            // 查询评论统计信息
            CommentAggregateDTO aggregate = commentAggregateService.getCommentAggregateByGoodsId(goodsId);
            commentShow.setAggregate(aggregate.clone(CommentAggregateVO.class));
            // 查询评论列表
            commentInfoQuery.setCommentStatus(CommentStatus.APPROVED);
            List<CommentInfoDTO> comments = commentInfoService.listByPage(commentInfoQuery);
            List<CommentInfoVO> targetComments
                    = comments
                    .stream()
                    .map(commentInfoDTO -> {
                        try {
                            CommentInfoVO targetComment = commentInfoDTO.clone(CommentInfoVO.class);
                            targetComment.setUsername(getEncryptedUsername(commentInfoDTO.getUsername()));
                            List<CommentPictureDTO> pictures = commentPictureService.listByCommentId(commentInfoDTO.getId());
                            targetComment.setPictures(ObjectUtils.convertList(pictures, CommentPictureVO.class));
                            return targetComment;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            commentShow.setComments(targetComments);
            return commentShow;
        } catch (Exception e) {
            logger.error("error", e);
            return new CommentShowVO();
        }
    }

    /**
     * 获取加密后的用户名
     *
     * @param username 用户名
     * @return 加密后的用户名
     */
    private String getEncryptedUsername(String username) {
        StringBuilder builder = new StringBuilder("");
        builder.append(username.charAt(0));
        for (int i = 1; i < username.length() - 1; i++) {
            builder.append("*");
        }
        builder.append(username.substring(username.length() - 1));
        return builder.toString();
    }
}
