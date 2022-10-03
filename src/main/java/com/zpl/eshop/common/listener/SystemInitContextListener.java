package com.zpl.eshop.common.listener;

import com.zpl.eshop.common.bean.SpringApplicationContext;
import com.zpl.eshop.schedule.stock.StockUpdateMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/2 13:51
 **/
@WebListener
public class SystemInitContextListener implements ServletContextListener {

    /**
     * spring容器
     */
    @Autowired
    private SpringApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("系统启动了。。。。。。。。");
        // ScheduleStockUpdateMessageConsumer stockUpdateMessageConsumer =
        //         context.getBean(ScheduleStockUpdateMessageConsumer.class);
        StockUpdateMessageConsumer stockUpdateMessageConsumer = context.getBean(StockUpdateMessageConsumer.class);
        stockUpdateMessageConsumer.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
