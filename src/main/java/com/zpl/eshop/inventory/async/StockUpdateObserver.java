package com.zpl.eshop.inventory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

/**
 * 商品库存更新结果观察者
 *
 * @author ZhangPeiL1n
 * @date 2022/2/12 14:35
 **/
@Component
public class StockUpdateObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(StockUpdateObserver.class);

    @Override
    public void update(Observable o, Object arg) {
        StockUpdateResult result = (StockUpdateResult) arg;
        logger.info("商品库存更新消息[messageId=" + result.getMessageId() + "]的异步处理结果为：" + result.getResult());
    }
}
