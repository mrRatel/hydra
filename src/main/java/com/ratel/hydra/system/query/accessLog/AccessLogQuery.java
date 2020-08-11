package com.ratel.hydra.system.query.accessLog;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ratel
 * @date 2020/6/21
 */
@Data
public class AccessLogQuery implements Serializable {
    private String operationContent;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
}
