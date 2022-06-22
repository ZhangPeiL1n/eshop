package com.zpl.eshop.comment.service;

import com.zpl.eshop.comment.constant.*;
import com.zpl.eshop.comment.dao.CommentInfoDAO;
import com.zpl.eshop.comment.domain.CommentInfoDO;
import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * 评论信息管理模块service组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/3 18:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentInfoServiceTest {
    /**
     * 评论信息管理模块Service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;

    /**
     * 评论信息管理模块DAO组件
     */
    @MockBean
    private CommentInfoDAO commentInfoDAO;

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
     * 测试手动新增评论信息
     */
    @Test
    public void testSaveManualPublishedComTentInfo() throws Exception {
        CommentInfoDTO partialCommentInfoDTO = createPartialCommentInfoDTO();
        CommentInfoDTO fullCommentInfoDTO = createFullCommentInfoDTO(partialCommentInfoDTO);
        CommentInfoDO commentInfoDO = convertCommentInfoDTO2DO(fullCommentInfoDTO);
        when(commentInfoDAO.saveCommentInfo(commentInfoDO)).thenReturn(1L);
        commentInfoService.saveManualPublishedCommentInfo(partialCommentInfoDTO);
        verify(commentInfoDAO, timeout(1)).saveCommentInfo(commentInfoDO);
        Assert.assertEquals(fullCommentInfoDTO, partialCommentInfoDTO);
    }

    /**
     * 测试自动发表评论
     */
    @Test
    public void testSaveAutoPublishedCommentInfo() throws Exception {
        OrderInfoDTO orderInfoDTO = createOrderInfoDTO();
        OrderItemDTO orderItemDTO = createOrderItemDTO();
        CommentInfoDTO commentInfoDTO = createAutoPublishedCommentInfoDTO(orderInfoDTO, orderItemDTO);
        CommentInfoDO commentInfoDO = convertCommentInfoDTO2DO(commentInfoDTO);
        when(commentInfoDAO.saveCommentInfo(commentInfoDO)).thenReturn(1L);
        CommentInfoDTO resultCommentInfoDTO = commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTO, orderItemDTO);
        verify(commentInfoDAO, times(1)).saveCommentInfo(commentInfoDO);
        Assert.assertEquals(commentInfoDTO, resultCommentInfoDTO);
    }

    /**
     * 创建一个含有部分数据的DTO对象
     *
     * @return
     * @throws Exception
     */
    private CommentInfoDTO createPartialCommentInfoDTO() throws Exception {
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        // 订单相关
        commentInfoDTO.setUserAccountId(1L);
        commentInfoDTO.setUsername("test");
        commentInfoDTO.setOrderInfoId(1L);
        commentInfoDTO.setOrderItemId(1L);
        commentInfoDTO.setGoodsId(1L);
        commentInfoDTO.setGoodsSkuId(1L);
        commentInfoDTO.setGoodsSkuSaleProperties("测试销售属性");
        // 评论相关
        commentInfoDTO.setCommentContent("测试评论");
        commentInfoDTO.setShowPicture(ShowPictures.YES);
        // 评分
        commentInfoDTO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDTO.setLogisticsScore(CommentInfoScore.FIVE);

        commentInfoDTO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDTO.setGmtModified(dateProvider.getCurrentTime());
        return commentInfoDTO;
    }

    /**
     * 创建完整的DTO对象
     *
     * @param partialCommentInfoDTO 部分数据的DTO
     * @return DTO
     * @throws Exception
     */
    private CommentInfoDTO createFullCommentInfoDTO(CommentInfoDTO partialCommentInfoDTO) throws Exception {
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setUserAccountId(partialCommentInfoDTO.getUserAccountId());
        commentInfoDTO.setUsername(partialCommentInfoDTO.getUsername());
        commentInfoDTO.setOrderInfoId(partialCommentInfoDTO.getOrderInfoId());
        commentInfoDTO.setOrderItemId(partialCommentInfoDTO.getOrderItemId());
        commentInfoDTO.setGoodsId(partialCommentInfoDTO.getGoodsId());
        commentInfoDTO.setGoodsSkuId(partialCommentInfoDTO.getGoodsSkuId());
        commentInfoDTO.setGoodsSkuSaleProperties(partialCommentInfoDTO.getGoodsSkuSaleProperties());
        commentInfoDTO.setCommentContent(partialCommentInfoDTO.getCommentContent());
        commentInfoDTO.setShowPicture(partialCommentInfoDTO.getShowPicture());
        commentInfoDTO.setGoodsScore(partialCommentInfoDTO.getGoodsScore());
        commentInfoDTO.setCustomerServiceScore(partialCommentInfoDTO.getCustomerServiceScore());
        commentInfoDTO.setLogisticsScore(partialCommentInfoDTO.getLogisticsScore());

        commentInfoDTO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDTO.setCommentStatus(CommentStatus.APPROVING);
        commentInfoDTO.setDefaultComment(DefaultComment.NO);
        commentInfoDTO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDTO.setGmtModified(dateProvider.getCurrentTime());
        return commentInfoDTO;
    }

    /**
     * 将 CommentInfoDTO 转换为 CommentInfoDO
     *
     * @param commentInfoDTO commentInfoDTO
     * @return commentInfoDO
     * @throws Exception
     */
    private CommentInfoDO convertCommentInfoDTO2DO(CommentInfoDTO commentInfoDTO) throws Exception {
        CommentInfoDO commentInfoDO = new CommentInfoDO();
        commentInfoDO.setUserAccountId(commentInfoDTO.getUserAccountId());
        commentInfoDO.setUsername(commentInfoDTO.getUsername());
        commentInfoDO.setOrderInfoId(commentInfoDTO.getOrderInfoId());
        commentInfoDO.setOrderItemId(commentInfoDTO.getOrderItemId());
        commentInfoDO.setGoodsId(commentInfoDTO.getGoodsId());
        commentInfoDO.setGoodsSkuId(commentInfoDTO.getGoodsSkuId());
        commentInfoDO.setGoodsSkuSaleProperties(commentInfoDTO.getGoodsSkuSaleProperties());
        commentInfoDO.setCommentContent(commentInfoDTO.getCommentContent());
        commentInfoDO.setShowPictures(commentInfoDTO.getShowPicture());
        commentInfoDO.setGoodsScore(commentInfoDTO.getGoodsScore());
        commentInfoDO.setCustomerServiceScore(commentInfoDTO.getCustomerServiceScore());
        commentInfoDO.setLogisticsScore(commentInfoDTO.getLogisticsScore());

        commentInfoDO.setTotalScore(commentInfoDTO.getTotalScore());
        commentInfoDO.setCommentType(commentInfoDTO.getCommentType());
        commentInfoDO.setCommentStatus(commentInfoDTO.getCommentStatus());
        commentInfoDO.setDefaultComment(commentInfoDTO.getDefaultComment());
        commentInfoDO.setGmtCreate(commentInfoDTO.getGmtCreate());
        commentInfoDO.setGmtModified(commentInfoDTO.getGmtModified());
        return commentInfoDO;
    }

    /**
     * 创建订单信息DTO
     *
     * @return 订单信息DTO
     * @throws Exception
     */
    private OrderInfoDTO createOrderInfoDTO() throws Exception {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setUserAccountId(1L);
        orderInfoDTO.setUsername("test");
        orderInfoDTO.setId(1L);
        return orderInfoDTO;
    }

    /**
     * 创建订单条目DTO
     *
     * @return 订单条目DTO
     * @throws Exception
     */
    private OrderItemDTO createOrderItemDTO() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1L);
        orderItemDTO.setGoodsId(1L);
        orderItemDTO.setGoodsId(1L);
        orderItemDTO.setSaleProperties("测试销售属性");
        return orderItemDTO;
    }

    /**
     * 创建评论信息DTO对象
     *
     * @param orderInfoDTO 订单信息DTO
     * @param orderItemDTO 订单条目DTO
     * @return 评论信息DTO对象
     */
    private CommentInfoDTO createAutoPublishedCommentInfoDTO(OrderInfoDTO orderInfoDTO, OrderItemDTO orderItemDTO) throws Exception {
        // 创建评论信息DTO对象
        CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
        commentInfoDTO.setUserAccountId(orderInfoDTO.getUserAccountId());
        commentInfoDTO.setUsername(orderInfoDTO.getUsername());
        commentInfoDTO.setOrderInfoId(orderInfoDTO.getId());
        commentInfoDTO.setOrderItemId(orderItemDTO.getId());
        commentInfoDTO.setGoodsId(orderItemDTO.getGoodsId());
        commentInfoDTO.setGoodsSkuId(orderItemDTO.getGoodsSkuId());
        commentInfoDTO.setGoodsSkuSaleProperties(orderItemDTO.getSaleProperties());
        // 计算总分数
        commentInfoDTO.setTotalScore(CommentInfoScore.FIVE);
        commentInfoDTO.setGoodsScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCustomerServiceScore(CommentInfoScore.FIVE);
        commentInfoDTO.setLogisticsScore(CommentInfoScore.FIVE);
        commentInfoDTO.setCommentContent(CommentContent.DEFAULT);
        commentInfoDTO.setShowPicture(ShowPictures.NO);
        // 设置是否是默认评论
        commentInfoDTO.setDefaultComment(DefaultComment.YES);
        // 设置评论状态
        commentInfoDTO.setCommentStatus(CommentStatus.APPROVED);
        // 设置评论的类型
        commentInfoDTO.setCommentType(CommentType.GOOD_COMMENT);
        commentInfoDTO.setGmtCreate(dateProvider.getCurrentTime());
        commentInfoDTO.setGmtModified(dateProvider.getCurrentTime());
        return commentInfoDTO;
    }
}
