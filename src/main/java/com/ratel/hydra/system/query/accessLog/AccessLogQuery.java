package com.ratel.hydra.system.query.accessLog;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ratel
 * @date 2020/6/21
 */
@Data
public class AccessLogQuery {
    private String operationContent;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
}
