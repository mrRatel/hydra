package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.constant.ExcelTypeEnum;
import com.ratel.hydra.system.dto.ExcelExportDTO;
import com.ratel.hydra.system.mapper.LoginLogMapper;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.ExcelExportQuery;
import com.ratel.hydra.system.query.loginLog.LoginLogExcelExport;
import com.ratel.hydra.system.service.ExcelService;
import com.ratel.hydra.system.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService, ExcelService<LoginLogExcelExport> {

    @Override
    public IPage<LoginLog> page(Page<LoginLog> page) {
        return page(page);
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
