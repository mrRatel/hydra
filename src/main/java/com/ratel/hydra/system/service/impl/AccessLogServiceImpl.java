package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.AccessLogMapper;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.accessLog.AccessLogQuery;
import com.ratel.hydra.system.service.AccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class AccessLogServiceImpl extends IBaseServiceImpl<AccessLogMapper, AccessLog> implements AccessLogService {


    @Override
    public void add(AccessLogAdd add) {
        AccessLog po = new AccessLog();
        BeanUtils.copyProperties(add,po);
        save(po);
    }

    @Override
    public IPage basePage(PageQuery query) {
        AccessLogQuery accessLogQuery = (AccessLogQuery) query.getQuery();
        LambdaQueryWrapper<AccessLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(AccessLog::getAccessTime);
        if (accessLogQuery != null) {
            if (StringUtils.isNoneBlank(accessLogQuery.getOperationContent())) {
                wrapper.eq(AccessLog::getOperationContent,accessLogQuery.getOperationContent());
            }
            if (accessLogQuery.getBeginTime() != null) {
                wrapper.ge(AccessLog::getAccessTime,accessLogQuery.getBeginTime());
            }
            if (accessLogQuery.getEndTime() != null) {
                wrapper.le(AccessLog::getAccessTime,accessLogQuery.getEndTime());
            }
        }
        Page<AccessLog> accessLogPage = new Page<>();
        Page page = query.getPage();
        BeanUtils.copyProperties(page,accessLogPage);
        return super.page(accessLogPage,wrapper);
    }
}
