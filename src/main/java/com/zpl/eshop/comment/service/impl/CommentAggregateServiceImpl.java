package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.CommentType;
import com.zpl.eshop.comment.constant.ShowPictures;
import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.common.util.DateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * 评论统计信息管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:53
 **/
@Service
public class CommentAggregateServiceImpl implements CommentAggregateService {

    private final Logger logger = LoggerFactory.getLogger(CommentAggregateServiceImpl.class);
    /**
     * 评论统计信息模块DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 更新评论统计信息
     *
     * @param commentInfoDTO 评论信息DTO
     * @return 更新是否成功
     */
    @Override
    public CommentAggregateDO refreshCommentAggregate(CommentInfoDTO commentInfoDTO) {
        CommentAggregateDO commentAggregateDO;
        try {
            commentAggregateDO = commentAggregateDAO.getCommentAggregateByGoodsId(commentInfoDTO.getGoodsId());
            if (commentAggregateDO == null) {
                commentAggregateDO = saveCommentAggregate(commentInfoDTO);
            } else {
                updateCommentAggregate(commentAggregateDO, commentInfoDTO);
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
        return commentAggregateDO;
    }

    /**
     * 新增评论统计信息
     *
     * @param commentInfoDTO 评论信息DTO
     */
    private CommentAggregateDO saveCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception {
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();
        commentAggregateDO.setGoodsId(commentInfoDTO.getGoodsId());
        commentAggregateDO.setTotalCommentCount(1L);
        commentAggregateDO.setGoodCommentCount(1L);
        commentAggregateDO.setMediumCommentCount(1L);
        commentAggregateDO.setBadCommentCount(1L);
        if (commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)) {
            commentAggregateDO.setGoodCommentCount(1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
            commentAggregateDO.setMediumCommentCount(1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)) {
            commentAggregateDO.setBadCommentCount(1L);
        }
        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format((double) commentAggregateDO.getGoodCommentCount() / (double) commentAggregateDO.getTotalCommentCount()));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);
        if (ShowPictures.YES.equals(commentInfoDTO.getShowPicture())) {
            commentAggregateDO.setShowPicturesCommentCount(1L);
        }
        commentAggregateDO.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDAO.saveCommentAggregate(commentAggregateDO);
        return commentAggregateDO;
    }

    /**
     * 更新评论统计信息
     *
     * @param commentAggregateDO 评论统计信息DO对象
     * @param commentInfoDTO     评论信息DTO
     */
    private void updateCommentAggregate(CommentAggregateDO commentAggregateDO, CommentInfoDTO commentInfoDTO) throws Exception {
        commentAggregateDO.setTotalCommentCount(commentAggregateDO.getTotalCommentCount() + 1L);
        if (commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)) {
            commentAggregateDO.setGoodCommentCount(commentAggregateDO.getGoodCommentCount() + 1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
            commentAggregateDO.setMediumCommentCount(commentAggregateDO.getMediumCommentCount() + 1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)) {
            commentAggregateDO.setBadCommentCount(commentAggregateDO.getBadCommentCount() + 1L);
        }
        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(commentAggregateDO.getGoodCommentCount() / commentAggregateDO.getTotalCommentCount()));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);
        if (ShowPictures.YES.equals(commentInfoDTO.getShowPicture())) {
            commentAggregateDO.setShowPicturesCommentCount(commentAggregateDO.getShowPicturesCommentCount() + 1L);
        }
        commentAggregateDO.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDAO.updateCommentAggregate(commentAggregateDO);
    }
}
