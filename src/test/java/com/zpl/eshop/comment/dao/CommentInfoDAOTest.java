package com.zpl.eshop.comment.dao;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoQuery;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Long commentInfoId = commentInfoDO.getId();
        Assert.assertNotNull(commentInfoId);
        Assert.assertThat(commentInfoId, Matchers.greaterThan(0L));
    }

    /**
     * 测试分页查询评论信息
     *
     * @throws Exception
     */
    @Test
    public void testListByPage() throws Exception {
        Map<Long, CommentInfoDO> commentInfoMap = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            CommentInfoDO comment = createCommentInfoDO();
            commentInfoMap.put(comment.getId(), comment);
        }
        Integer offsite = 10;
        Integer size = 5;
        CommentInfoQuery query = new CommentInfoQuery();
        query.setCommentStatus(CommentStatus.APPROVING);
        query.setCommentType(null);
        query.setDefaultComment(DefaultComment.NO);
        query.setStartTime(dateProvider.parse2Datetime("2022-01-01 00:00:00"));
        query.setEndTime(dateProvider.parse2Datetime("2022-12-01 00:00:00"));
        query.setOffsite(offsite);
        query.setSize(size);
        query.setShowPictures(ShowPictures.YES);
        query.setTotalScore(CommentInfoScore.FIVE);

        List<CommentInfoDO> resultComments = commentInfoDAO.listByPage(query);

        Assert.assertEquals((int) size, resultComments.size());
        resultComments.forEach(resultComment -> Assert.assertEquals(commentInfoMap.get(resultComment.getId()), resultComment));
    }

    /**
     * 测试根据id获取评论详情
     *
     * @throws Exception
     */
    @Test
    public void testGetById() throws Exception {
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        CommentInfoDO resultComment = commentInfoDAO.getById(commentInfoDO.getId());
        Assert.assertEquals(commentInfoDO, resultComment);
    }

    /**
     * 测试更新评论
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        commentInfoDO.setCommentStatus(CommentStatus.APPROVED);
        commentInfoDO.setGmtModified(dateProvider.getCurrentTime());
        commentInfoDAO.update(commentInfoDO);

        CommentInfoDO resultComment = commentInfoDAO.getById(commentInfoDO.getId());
        Assert.assertEquals(commentInfoDO, resultComment);
    }

    /**
     * 测试删除评论
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        CommentInfoDO commentInfoDO = createCommentInfoDO();
        commentInfoDAO.delete(commentInfoDO.getId());
        CommentInfoDO resultComment = commentInfoDAO.getById(commentInfoDO.getId());
        Assert.assertNull(resultComment);
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
        commentInfoDO.setShowPictures(ShowPictures.YES);
        // 评分
        commentInfoDO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDO.setLogisticsScore(CommentInfoScore.FIVE);

        commentInfoDO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDO.setGmtModified(dateProvider.getCurrentTime());
        commentInfoDAO.saveCommentInfo(commentInfoDO);
        return commentInfoDO;
    }
}
