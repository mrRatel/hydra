package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.loginLog.LoginLogQuery;

/**
 * @author ratel
 * @date 2020-06-05
 */
public interface LoginLogService {

    void add(LoginLog loginLog);
}
