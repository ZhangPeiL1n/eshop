package com.zpl.eshop.comment.schedule;

import com.zpl.eshop.comment.domain.CommentInfoDTO;
import com.zpl.eshop.comment.service.CommentAggregateService;
import com.zpl.eshop.comment.service.CommentInfoService;
import com.zpl.eshop.order.domain.OrderInfoDTO;
import com.zpl.eshop.order.domain.OrderItemDTO;
import com.zpl.eshop.order.service.OrderFacadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 自动发表评论调度任务单元测试类
 *
 * @author ZhangPeiL1n
 * @date 2022/2/5 10:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoPublishCommentTaskTest {

    /**
     * 自动发表评论调度任务
     */
    @Autowired
    private AutoPublishCommentTask autoPublishCommentTask;

    /**
     * 订单中心对外接口Service组件
     */
    @MockBean
    private OrderFacadeService orderFacadeService;

    /**
     * 评论信息管理模块Service组件
     */
    @MockBean
    private CommentInfoService commentInfoService;

    /**
     * 评论统计信息Service组件
     */
    @MockBean
    private CommentAggregateService commentAggregateService;

    @Test
    public void testExecute() throws Exception {
        List<OrderInfoDTO> orderInfoDTOs = createOrderInfoDTOs();
        ArrayList<Long> orderInfoIds = new ArrayList<>();
        for (OrderInfoDTO orderInfoDTO : orderInfoDTOs) {
            orderInfoIds.add(orderInfoDTO.getId());
        }
        when(orderFacadeService.listNotPublishCommentOrders()).thenReturn(orderInfoDTOs);
        CommentInfoDTO commentInfoDTO1 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOs.get(0), orderInfoDTOs.get(0).getOrderItems().get(0))).thenReturn(commentInfoDTO1);
        CommentInfoDTO commentInfoDTO2 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOs.get(0), orderInfoDTOs.get(0).getOrderItems().get(1))).thenReturn(commentInfoDTO2);
        CommentInfoDTO commentInfoDTO3 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOs.get(1), orderInfoDTOs.get(1).getOrderItems().get(0))).thenReturn(commentInfoDTO3);
        CommentInfoDTO commentInfoDTO4 = new CommentInfoDTO();
        when(commentInfoService.saveAutoPublishedCommentInfo(orderInfoDTOs.get(1), orderInfoDTOs.get(1).getOrderItems().get(1))).thenReturn(commentInfoDTO4);

        autoPublishCommentTask.execute();

        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOs.get(0), orderInfoDTOs.get(0).getOrderItems().get(0));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOs.get(0), orderInfoDTOs.get(0).getOrderItems().get(1));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOs.get(1), orderInfoDTOs.get(1).getOrderItems().get(0));
        verify(commentInfoService, times(1)).saveAutoPublishedCommentInfo(orderInfoDTOs.get(1), orderInfoDTOs.get(1).getOrderItems().get(1));
        verify(commentAggregateService, times(4)).refreshCommentAggregate(commentInfoDTO1);

        verify(orderFacadeService, times(1)).informBatchPublishCommentEvent(orderInfoIds);
    }

    /**
     * 创建订单信息DTO集合
     *
     * @return 订单信息DTO集合
     */
    private List<OrderInfoDTO> createOrderInfoDTOs() throws Exception {
        // 构造第一个orderInfoDTO
        OrderInfoDTO orderInfoDTO1 = new OrderInfoDTO();
        orderInfoDTO1.setId(1L);
        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        orderItemDTO1.setId(1L);
        orderItemDTO1.setGoodsId(1L);
        orderItemDTO1.setGoodsSkuId(1L);
        OrderItemDTO orderItemDTO2 = new OrderItemDTO();
        orderItemDTO2.setId(2L);
        orderItemDTO2.setGoodsId(2L);
        orderItemDTO2.setGoodsSkuId(2L);
        ArrayList<OrderItemDTO> orderItemDTOs1 = new ArrayList<>();
        orderItemDTOs1.add(orderItemDTO1);
        orderItemDTOs1.add(orderItemDTO2);
        orderInfoDTO1.setOrderItems(orderItemDTOs1);
        // 构造第二个orderInfoDTO
        OrderInfoDTO orderInfoDTO2 = new OrderInfoDTO();
        orderInfoDTO2.setId(3L);
        OrderItemDTO orderItemDTO3 = new OrderItemDTO();
        orderItemDTO3.setId(4L);
        orderItemDTO3.setGoodsId(1L);
        orderItemDTO3.setGoodsSkuId(1L);
        OrderItemDTO orderItemDTO4 = new OrderItemDTO();
        orderItemDTO4.setId(2L);
        orderItemDTO4.setGoodsId(2L);
        orderItemDTO4.setGoodsSkuId(2L);
        ArrayList<OrderItemDTO> orderItemDTOs2 = new ArrayList<>();
        orderItemDTOs2.add(orderItemDTO1);
        orderItemDTOs2.add(orderItemDTO2);
        orderInfoDTO2.setOrderItems(orderItemDTOs2);

        ArrayList<OrderInfoDTO> orderInfoDTOs = new ArrayList<>();
        orderInfoDTOs.add(orderInfoDTO1);
        orderInfoDTOs.add(orderInfoDTO2);
        return orderInfoDTOs;

    }
}
