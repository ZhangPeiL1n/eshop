package com.zpl.eshop.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author ZhangPeiL1n
 * @date 2022/1/23 12:15
 **/
public class DateUtils {
    /**
     * 以线程安全的方式获取DateFormat
     *
     * @return DateFormat
     */
    public static Date getCurrentTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(simpleDateFormat.format(new Date()));
    }
}
