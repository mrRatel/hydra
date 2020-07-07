package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import org.springframework.scheduling.annotation.Async;

/**
 * @author ratel
 * @date 2020-05-21
 */
public interface AccessLogService {

    /**
     * @Description 增加一条访问记录
     * @Author      ratel
     * @Date        2020-05-21
     * @param       add
     * @return      void
     **/
    void add(AccessLogAdd add);
    
    /**
     * @Description 异步增加一条访问记录
     * @Author      ratel
     * @Date        2020-07-07
     * @param       add
     * @return      void
     **/
    @Async
    void asyncAdd(AccessLogAdd add);
}
