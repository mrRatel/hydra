package com.ratel.hydra.common.adapter;

import com.ratel.hydra.common.constant.ExcelTypeEnum;
import com.ratel.hydra.system.dto.ExcelExportDTO;
import com.ratel.hydra.system.query.ExcelExportQuery;
import com.ratel.hydra.system.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Component("excelServiceAdapter")
public class ExcelServiceAdapter implements ExcelService<ExcelExportQuery> {

    private static Map<ExcelTypeEnum,ExcelService> excelServiceMap = new HashMap<>();

    @Override
    public ExcelExportDTO exportExcel(ExcelExportQuery excelExportQuery) {
        return null;
    }

    @Override
    public ExcelTypeEnum excelType() {
        return null;
    }
    public static void regist(ExcelTypeEnum type,ExcelService excelService){
        if (excelService instanceof ExcelServiceAdapter){
            log.info("适配器自己不用注册");
            return;
        }
        log.info("====>>注册excel服务 【{}】",type.toString());
        excelServiceMap.put(type,excelService);
    }

    public static Class getTypeClass(ExcelTypeEnum excelTypeEnum){
        ExcelService excelService = excelServiceMap.get(excelServiceMap);
        if (excelService == null){
            return null;
        }
        return null;
    }
}
