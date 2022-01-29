package com.zpl.eshop.common.util;

import java.util.Date;

/**
 * 日期辅助组件接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:48
 **/
public interface DateProvider {

    /**
     * 获取当前时间
     *
     * @return 当前时间
     * @throws Exception
     */
    Date getCurrentTime() throws Exception;
}
