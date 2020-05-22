package com.ratel.hydra.system.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.AccessLog;
import lombok.Data;

import java.util.Date;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Data
public class AccessLogListQuery {
    private Page<AccessLog> page = new Page<>(1,50);
    private Date accessTime;
}
