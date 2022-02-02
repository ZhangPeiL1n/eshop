package com.zpl.eshop.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 12:15
 **/
@Component
public class DateProviderImpl implements DateProvider {
    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    @Override
    public Date getCurrentTime() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(simpleDateFormat.format(new Date()));
    }

    /**
     * 将 Date 对象格式化成：yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期对象
     * @return 格式化后字符串
     * @throws Exception
     */
    @Override
    public String formatDateTime(Date date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
