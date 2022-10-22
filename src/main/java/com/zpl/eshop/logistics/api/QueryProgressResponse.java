package com.zpl.eshop.logistics.api;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2022/10/22 23:57
 **/
@Data
public class QueryProgressResponse {

    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 0;

    /**
     * 响应状态
     */
    private Integer status;

    /**
     * 追踪活动
     */
    private List<TraceEvent> traceEvents;

    /**
     * 物流追踪活动
     */
    @Data
    public static class TraceEvent {

        /**
         * 活动发生的时间
         */
        private Date time;

        /**
         * 活动的描述
         */
        private String description;

        public TraceEvent(Date time, String description) {
            this.time = time;
            this.description = description;
        }
    }

}
