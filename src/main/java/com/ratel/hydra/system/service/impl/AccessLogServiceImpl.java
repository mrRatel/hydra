package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.AccessLogMapper;
import com.ratel.hydra.system.po.AccessLog;
import com.ratel.hydra.system.query.AccessLogAdd;
import com.ratel.hydra.system.service.AccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLog> implements AccessLogService {

    @Override
    public IPage<AccessLog> list(Page<AccessLog> query) {
        LambdaQueryWrapper<AccessLog> eq = new LambdaQueryWrapper<AccessLog>();
//        .eq(AccessLog::getId,1);
        return baseMapper.selectPage(query, eq);
    }

    @Override
    public void add(AccessLogAdd add) {
        AccessLog po = new AccessLog();
        BeanUtils.copyProperties(add,po);
        baseMapper.insert(po);
    }
}
