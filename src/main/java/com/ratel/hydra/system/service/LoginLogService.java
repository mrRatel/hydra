package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.LoginLog;

/**
 * @author ratel
 * @date 2020-06-05
 */
public interface LoginLogService {

    IPage<LoginLog> page(Page<LoginLog> page);

    void add(LoginLog loginLog);
}
