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
import java.util.Date;

/**
 * 评论统计信息管理模块Service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/1/18 21:53
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentAggregateServiceImpl implements CommentAggregateService {

    /**
     * 评论统计管理DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;

    @Override
    public Boolean updateCommentAggregate(CommentInfoDTO commentInfo) throws Exception {

        CommentAggregateDO commentAggregate = commentAggregateDAO.getCommentAggregateByGoodsId(commentInfo.getGoodsId());
        if (commentAggregate == null) {
            commentAggregate = new CommentAggregateDO();
            commentAggregate.setGoodsId(commentInfo.getGoodsId());
            commentAggregate.setTotalCommentCount(1L);
            if (commentInfo.getCommentType().equals(CommentType.GOOD_COMMENT)) {
                commentAggregate.setGoodCommentCount(1L);
            } else if (commentInfo.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
                commentAggregate.setMediumCommentCount(1L);
            } else if (commentInfo.getCommentType().equals(CommentType.BAD_COMMENT)) {
                commentAggregate.setBadCommentCount(1L);
            }
            Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(commentAggregate.getGoodCommentCount() / commentAggregate.getTotalCommentCount()));
            commentAggregate.setGoodCommentRate(goodCommentRate);
            if (ShowPicture.YES.equals(commentInfo.getShowPicture())) {
                commentAggregate.setShowPictureCommentCount(1L);
            }
            commentAggregate.setGmtCreate(new Date());
            commentAggregate.setGmtModified(new Date());
            commentAggregateDAO.saveCommentAggregate(commentAggregate);
        } else {
            commentAggregate.setTotalCommentCount(commentAggregate.getTotalCommentCount() + 1L);
            if (commentInfo.getCommentType().equals(CommentType.GOOD_COMMENT)) {
                commentAggregate.setGoodCommentCount(commentAggregate.getGoodCommentCount() + 1L);
            } else if (commentInfo.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
                commentAggregate.setMediumCommentCount(commentAggregate.getMediumCommentCount() + 1L);
            } else if (commentInfo.getCommentType().equals(CommentType.BAD_COMMENT)) {
                commentAggregate.setBadCommentCount(commentAggregate.getBadCommentCount() + 1L);
            }
            Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(commentAggregate.getGoodCommentCount() / commentAggregate.getTotalCommentCount()));
            commentAggregate.setGoodCommentRate(goodCommentRate);
            if (ShowPicture.YES.equals(commentInfo.getShowPicture())) {
                commentAggregate.setShowPictureCommentCount(commentAggregate.getShowPictureCommentCount() + 1L);
            }
            commentAggregate.setGmtModified(new Date());
            commentAggregateDAO.updateCommentAggregate(commentAggregate);
        }
        return true;
    }
}
