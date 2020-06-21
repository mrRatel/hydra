package com.ratel.hydra.system.query.loginLog;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author ratel
 * @date 2020/6/21
 */
@Data
public class LoginLogQuery {
    private String username;
    @DateTimeFormat
    private LocalDateTime beginTime;
    @DateTimeFormat
    private LocalDateTime endTime;
}
