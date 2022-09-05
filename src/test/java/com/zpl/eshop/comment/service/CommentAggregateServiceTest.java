package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.constant.CommentType;
import com.zpl.eshop.comment.constant.ShowPictures;
import com.zpl.eshop.comment.dao.CommentAggregateDAO;
import com.zpl.eshop.comment.domain.CommentAggregateDO;
import com.zpl.eshop.comment.domain.CommentAggregateDTO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.common.util.DateProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * 评论信息统计单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/4 12:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentAggregateServiceTest {

    /**
     * 评论统计信息Service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 评论统计信息DAO组件
     */
    @MockBean
    private CommentAggregateDAO commentAggregateDAO;

    /**
     * 日期辅助组件
     */
    @MockBean
    private DateProvider dateProvider;

    @Before
    public void setup() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        when(dateProvider.getCurrentTime()).thenReturn(currentTime);
    }

    /**
     * 测试保存评论统计信息
     */
    @Test
    public void testSaveCommentAggregate() throws Exception {
        Long goodsId = 1L;
        when(commentAggregateDAO.getCommentAggregateByGoodsId(goodsId)).thenReturn(null);
        CommentInfoDTO commentInfoDTO = createCommentInfoDTO();
        CommentAggregateDTO commentAggregate = commentAggregateService.refreshCommentAggregate(commentInfoDTO);
        verify(commentAggregateDAO, times(1)).getCommentAggregateByGoodsId(goodsId);
        assertNotNull(commentAggregate);
        assertEquals(goodsId, commentAggregate.getGoodsId());
        assertEquals(Long.valueOf(1L), commentAggregate.getGoodCommentCount());
        assertEquals(Long.valueOf(1L), commentAggregate.getTotalCommentCount());
        assertEquals(Double.valueOf(1.00), commentAggregate.getGoodCommentRate());
        assertEquals(Long.valueOf(1L), commentAggregate.getShowPicturesCommentCount());
        assertEquals(dateProvider.getCurrentTime(), commentAggregate.getGmtCreate());
        assertEquals(dateProvider.getCurrentTime(), commentAggregate.getGmtModified());
    }

    /**
     * 测试更新评论统计信息
     */
    @Test
    public void testUpdateCommentAggregate() throws Exception {
        Long goodsId = 1L;
        CommentAggregateDO initialCommentAggregateDO = createCommentAggregateDO();
        CommentAggregateDO CommentAggregateDO = createCommentAggregateDO();
        when(commentAggregateDAO.getCommentAggregateByGoodsId(goodsId)).thenReturn(CommentAggregateDO);
        CommentInfoDTO commentInfoDTO = createCommentInfoDTO();
        CommentAggregateDTO resultCommentAggregate = commentAggregateService.refreshCommentAggregate(commentInfoDTO);
        assertEquals(Long.valueOf(initialCommentAggregateDO.getTotalCommentCount() + 1L), resultCommentAggregate.getTotalCommentCount());
        assertEquals(Long.valueOf(initialCommentAggregateDO.getGoodCommentCount() + 1L), resultCommentAggregate.getGoodCommentCount());
        Double expectedGoodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(initialCommentAggregateDO.getGoodCommentCount() / initialCommentAggregateDO.getTotalCommentCount()));
        assertEquals(expectedGoodCommentRate, initialCommentAggregateDO.getGoodCommentRate());
        assertEquals(Long.valueOf(initialCommentAggregateDO.getShowPicturesCommentCount() + 1L), resultCommentAggregate.getShowPicturesCommentCount());

    }

    /**
     * 创建评论信息DTO对象
     *
     * @return 评论信息DTO对象
     */
    public CommentInfoDTO createCommentInfoDTO() {
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setGoodsId(1L);
        commentInfoDTO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDTO.setShowPicture(ShowPictures.YES);
        return commentInfoDTO;
    }

    /**
     * 创建CommentAggregateDO对象
     *
     * @return com
     */
    private CommentAggregateDO createCommentAggregateDO() {
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();
        commentAggregateDO.setTotalCommentCount(1L);
        commentAggregateDO.setGoodCommentCount(1L);
        commentAggregateDO.setShowPicturesCommentCount(1L);
        commentAggregateDO.setGoodCommentRate(1.00);
        return commentAggregateDO;
    }
}
