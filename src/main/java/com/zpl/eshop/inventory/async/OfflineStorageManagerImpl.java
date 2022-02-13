package com.zpl.eshop.inventory.async;

import com.alibaba.fastjson.JSONObject;
import com.zpl.eshop.common.util.DateProvider;
import com.zpl.eshop.inventory.dao.StockUpdateMessageDAO;
import com.zpl.eshop.inventory.domain.StockUpdateMessageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 离线存储管理组件
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 22:28
 **/
@Component
public class OfflineStorageManagerImpl implements OfflineStorageManager {

    /**
     * 库存更新消息管理模块DAO组件
     */
    @Autowired
    private StockUpdateMessageDAO stockUpdateMessageDAO;

    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;

    /**
     * 是否触发离线存储
     */
    private Boolean offline = false;

    /**
     * 获取离线标识
     *
     * @return 离线标识
     */
    @Override
    public Boolean getOffline() {
        return offline;
    }

    /**
     * 设置离线标识
     *
     * @param offline 离线标识
     */
    @Override
    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    /**
     * 离线存储库存更新消息
     *
     * @param message 库存更新消息
     * @throws Exception
     */
    @Override
    public void store(StockUpdateMessage message) throws Exception {
        StockUpdateMessageDO stockUpdateMessageDO = createStockUpdateMessageDO(message);
        stockUpdateMessageDAO.save(stockUpdateMessageDO);
    }

    /**
     * 创建库存更新消息DO对象
     *
     * @param message 库存消息
     * @return 库存更新消息DO对象
     * @throws Exception
     */
    public StockUpdateMessageDO createStockUpdateMessageDO(StockUpdateMessage message) throws Exception {
        StockUpdateMessageDO stockUpdateMessageDO = new StockUpdateMessageDO();
        stockUpdateMessageDO.setMessageId(message.getId());
        stockUpdateMessageDO.setOperation(message.getOperation());
        stockUpdateMessageDO.setParameter(JSONObject.toJSONString(stockUpdateMessageDO.getParameter()));
        stockUpdateMessageDO.setParameterClazz(stockUpdateMessageDO.getParameter().getClass().getName());
        stockUpdateMessageDO.setGmtCreate(dateProvider.getCurrentTime());
        stockUpdateMessageDO.setGmtModified(dateProvider.getCurrentTime());
        return stockUpdateMessageDO;
    }

    /**
     * 判断是否还有下一批库存更新消息
     *
     * @return true 还有库存更新消息
     */
    @Override
    public Boolean hasNext() {
        return stockUpdateMessageDAO.count() > 0;
    }

    /**
     * 离线恢复线程使用，每次批量查询50条
     *
     * @return 50条数据
     */
    @Override
    public List<StockUpdateMessage> getNextBatch() throws Exception {
        List<StockUpdateMessage> stockUpdateMessages = new ArrayList<>();
        List<StockUpdateMessageDO> stockUpdateMessageDOList = stockUpdateMessageDAO.listByBatch();
        for (StockUpdateMessageDO stockUpdateMessageDO : stockUpdateMessageDOList) {
            StockUpdateMessage stockUpdateMessage = new StockUpdateMessage();
            stockUpdateMessage.setId(stockUpdateMessageDO.getMessageId());
            stockUpdateMessage.setOperation(stockUpdateMessageDO.getOperation());
            stockUpdateMessage.setParameter(JSONObject.parseObject(stockUpdateMessageDO.getParameter(), Class.forName(stockUpdateMessageDO.getParameterClazz())));
            stockUpdateMessages.add(stockUpdateMessage);
        }
        return stockUpdateMessages;
    }

    /**
     * 批量删除
     *
     * @param messages 库存更新消息集合
     * @return true 删除成功
     */
    @Override
    public Boolean removeByBatch(List<StockUpdateMessage> messages) {
        List<String> messageIdsList = messages.stream().map(StockUpdateMessage::getId).collect(Collectors.toList());
        String messageIds = String.join(",", messageIdsList);
        stockUpdateMessageDAO.removeByBatch(messageIds);
        return true;
    }

    /**
     * 查询库存消息表的记录数
     *
     * @return 记录数
     */
    @Override
    public Long count() {
        return stockUpdateMessageDAO.count();
    }
}
