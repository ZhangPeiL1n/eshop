package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.CommentType;
import com.zpl.eshop.comment.constant.ShowPicture;
import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

/**
 * 评论统计信息管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:53
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentAggregateServiceImpl implements CommentAggregateService {

    @Autowired
    private CommentAggregateDAO commentAggregateDAO;

    /**
     * 更新评论统计信息
     *
     * @param commentInfo 评论信息DTO
     * @return 更新是否成功
     * @throws Exception
     */
    @Override
    public Boolean updateCommentAggregate(CommentInfoDTO commentInfo) throws Exception {
        CommentAggregateDO commentAggregateDO = commentAggregateDAO.getCommentAggregateByGoodsId(commentInfo.getGoodsId());
        if (commentAggregateDO == null) {
            commentAggregateDO = new CommentAggregateDO();
            commentAggregateDO.setGoodsId(commentInfo.getGoodsId());
            commentAggregateDO.setTotalCommentCount(1L);
            if (commentInfo.getCommentType().equals(CommentType.GOOD_COMMENT)) {
                commentAggregateDO.setGoodCommentCount(1L);
            } else if (commentInfo.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
                commentAggregateDO.setMediumCommentCount(1L);
            } else if (commentInfo.getCommentType().equals(CommentType.BAD_COMMENT)) {
                commentAggregateDO.setBadCommentCount(1L);
            }
            Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(commentAggregateDO.getGoodCommentCount() / commentAggregateDO.getTotalCommentCount()));
            commentAggregateDO.setGoodCommentRate(goodCommentRate);
            if (ShowPicture.YES.equals(commentInfo.getShowPicture())) {
                commentAggregateDO.setShowPictureCommentCount(1L);
            }
            commentAggregateDAO.saveCommentAggregate(commentAggregateDO);
        } else {
            commentAggregateDO.setTotalCommentCount(commentAggregateDO.getTotalCommentCount() + 1L);
            if (commentInfo.getCommentType().equals(CommentType.GOOD_COMMENT)) {
                commentAggregateDO.setGoodCommentCount(commentAggregateDO.getGoodCommentCount() + 1L);
            } else if (commentInfo.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
                commentAggregateDO.setMediumCommentCount(commentAggregateDO.getMediumCommentCount() + 1L);
            } else if (commentInfo.getCommentType().equals(CommentType.BAD_COMMENT)) {
                commentAggregateDO.setBadCommentCount(commentAggregateDO.getBadCommentCount() + 1L);
            }
            Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(commentAggregateDO.getGoodCommentCount() / commentAggregateDO.getTotalCommentCount()));
            commentAggregateDO.setGoodCommentRate(goodCommentRate);
            if (ShowPicture.YES.equals(commentInfo.getShowPicture())) {
                commentAggregateDO.setShowPictureCommentCount(commentAggregateDO.getShowPictureCommentCount() + 1L);
            }
            commentAggregateDAO.updateCommentAggregate(commentAggregateDO);
        }
        return true;
    }
}
