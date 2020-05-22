package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.query.AccessLogListQuery;

/**
 * @author ratel
 * @date 2020-05-21
 */
public interface AccessLogService {

    /**
     * @Description 获取访问记录
     * @Author      ratel
     * @Date        2020-05-21
     * @param       query
     * @return      com.baomidou.mybatisplus.core.metadata.IPage<com.ratel.hydra.system.po.AccessLog>
     **/
    IPage<AccessLog> list(Page<AccessLog> query);

    /**
     * @Description 增加一条访问记录
     * @Author      ratel
     * @Date        2020-05-21
     * @param       add
     * @return      void
     **/
    void add(AccessLogAdd add);
}
