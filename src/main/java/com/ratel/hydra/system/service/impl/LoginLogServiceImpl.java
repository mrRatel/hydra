package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.constant.ExcelTypeEnum;
import com.ratel.hydra.system.dto.ExcelExportDTO;
import com.ratel.hydra.system.mapper.LoginLogMapper;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.ExcelExportQuery;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.query.loginLog.LoginLogExcelExport;
import com.ratel.hydra.system.query.loginLog.LoginLogQuery;
import com.ratel.hydra.system.service.ExcelService;
import com.ratel.hydra.system.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends IBaseServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService, ExcelService<LoginLogExcelExport> {

    @Override
    public IPage basePage(PageQuery page) {
        LambdaQueryWrapper<LoginLog> wrapper = new LambdaQueryWrapper<>();
        LoginLogQuery query = (LoginLogQuery) page.getQuery();
        if (query != null) {
            if (StringUtils.isNoneBlank(query.getUsername())) {
                wrapper.eq(LoginLog::getUsername, query.getUsername());
            }
            if (query.getBeginTime() != null) {
                wrapper.ge(LoginLog::getLoginTime, query.getBeginTime());
            }
            if (query.getEndTime() != null) {
                wrapper.le(LoginLog::getLoginTime, query.getEndTime());
            }
        }
        Page<LoginLog> queryPage = new Page<>();
        page.setPage(null);
        BeanUtils.copyProperties(page, queryPage);
        return super.page(queryPage, wrapper);
    }

    @Override
    public void add(LoginLog loginLog) {
        save(loginLog);
    }

    @Override
    public ExcelExportDTO exportExcel(LoginLogExcelExport loginLogExcelExport) {
        return null;
    }

    @Override
    public ExcelTypeEnum excelType() {
        return ExcelTypeEnum.LOGIN_LOG;
    }
}
