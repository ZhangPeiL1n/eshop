package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.common.util.DateProvider;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论信息管理模块DAO组件单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/2 18:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CommentInfoDAOTest {

    /**
     * 评论信息管理模块DAO组件
     */
    @Autowired
    private CommentInfoDAO commentInfoDAO;

    @Autowired
    private DateProvider dateProvider;

    /**
     * 测试新增评论信息
     */
    @Test
    public void testSaveCommentInfo() throws Exception {
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        Long commentInfoId = commentInfoDAO.saveCommentInfo(commentInfoDO);
        Assert.assertNotNull(commentInfoId);
        Assert.assertThat(commentInfoId, Matchers.greaterThan(0L));
    }

    /**
     * 创建评论信息DO
     *
     * @return DO
     * @throws Exception
     */
    private CommentInfoDO createCommentInfoDO() throws Exception {
        CommentInfoDO commentInfoDO = new CommentInfoDO();
        // 订单相关
        commentInfoDO.setUserAccountId(1L);
        commentInfoDO.setUsername("test");
        commentInfoDO.setOrderInfoId(1L);
        commentInfoDO.setOrderItemId(1L);
        commentInfoDO.setGoodsId(1L);
        commentInfoDO.setGoodsSkuId(1L);
        commentInfoDO.setGoodsSkuSaleProperties("测试销售属性");
        // 评论相关
        commentInfoDO.setCommentContent("测试评论");
        commentInfoDO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDO.setCommentStatus(CommentStatus.APPROVING);
        commentInfoDO.setDefaultComment(DefaultComment.NO);
        commentInfoDO.setShowPicture(ShowPictures.YES);
        // 评分
        commentInfoDO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDO.setLogisticsScore(CommentInfoScore.FIVE);

        commentInfoDO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDO.setGmtModified(dateProvider.getCurrentTime());
        return commentInfoDO;
    }
}
