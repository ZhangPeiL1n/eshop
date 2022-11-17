package com.zpl.eshop.comment.service.impl;

import com.zpl.eshop.comment.constant.CommentType;
import com.zpl.eshop.comment.constant.ShowPictures;
import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.domain.CommentAggregateDTO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.common.util.DateProvider;
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
     * @param commentInfo 评论信息DTO
     * @return 更新是否成功
     */
    @Override
    public CommentAggregateDTO refreshCommentAggregate(CommentInfoDTO commentInfo) throws Exception {
        CommentAggregateDO commentAggregate = commentAggregateDAO.getCommentAggregateByGoodsId(commentInfo.getGoodsId());
        if (commentAggregate == null) {
            commentAggregate = saveCommentAggregate(commentInfo);
        } else {
            updateCommentAggregate(commentAggregate, commentInfo);
        }
        return commentAggregate.clone(CommentAggregateDTO.class);
    }

    /**
     * 根据商品 id 查询评论统计信息
     *
     * @param goodsId 商品id
     * @return 评论统计信息
     */
    @Override
    public CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception {
        return commentAggregateDAO.getCommentAggregateByGoodsId(goodsId).clone(CommentAggregateDTO.class);
    }

    /**
     * 新增评论统计信息
     *
     * @param commentInfo 评论信息DTO
     */
    private CommentAggregateDO saveCommentAggregate(CommentInfoDTO commentInfo) throws Exception {
        CommentAggregateDO commentAggregate = new CommentAggregateDO();
        commentAggregate.setGoodsId(commentInfo.getGoodsId());
        commentAggregate.setTotalCommentCount(1L);
        commentAggregate.setGoodCommentCount(1L);
        commentAggregate.setMediumCommentCount(1L);
        commentAggregate.setBadCommentCount(1L);
        if (commentInfo.getCommentType().equals(CommentType.GOOD_COMMENT)) {
            commentAggregate.setGoodCommentCount(1L);
        } else if (commentInfo.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
            commentAggregate.setMediumCommentCount(1L);
        } else if (commentInfo.getCommentType().equals(CommentType.BAD_COMMENT)) {
            commentAggregate.setBadCommentCount(1L);
        }
        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format((double) commentAggregate.getGoodCommentCount() / (double) commentAggregate.getTotalCommentCount()));
        commentAggregate.setGoodCommentRate(goodCommentRate);
        if (ShowPictures.YES.equals(commentInfo.getShowPictures())) {
            commentAggregate.setShowPicturesCommentCount(1L);
        }
        commentAggregate.setGmtCreate(dateProvider.getCurrentTime());
        commentAggregate.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDAO.saveCommentAggregate(commentAggregate);
        return commentAggregate;
    }

    /**
     * 更新评论统计信息
     *
     * @param commentAggregate 评论统计信息DO对象
     * @param commentInfo      评论信息DTO
     */
    private void updateCommentAggregate(CommentAggregateDO commentAggregate, CommentInfoDTO commentInfo) throws Exception {
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
        if (ShowPictures.YES.equals(commentInfo.getShowPictures())) {
            commentAggregate.setShowPicturesCommentCount(commentAggregate.getShowPicturesCommentCount() + 1L);
        }
        commentAggregate.setGmtModified(dateProvider.getCurrentTime());
        commentAggregateDAO.updateCommentAggregate(commentAggregate);
    }
}
