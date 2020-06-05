package com.ratel.hydra.system.service;

import com.ratel.hydra.common.adapter.ExcelServiceAdapter;
import com.ratel.hydra.common.constant.ExcelTypeEnum;
import com.ratel.hydra.system.dto.ExcelExportDTO;
import com.ratel.hydra.system.query.ExcelExportQuery;

import javax.annotation.PostConstruct;

public interface ExcelService<T extends ExcelExportQuery>{

        ExcelExportDTO exportExcel(T t);

        ExcelTypeEnum excelType();

        @PostConstruct
        default void registerExcelService(){

        ExcelServiceAdapter.regist(excelType(),this);
        }

}
