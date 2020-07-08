package com.ratel.hydra.system.controller;

import com.alibaba.excel.EasyExcel;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.dto.ExcelExportDTO;
import com.ratel.hydra.system.query.ExcelExportQuery;
import com.ratel.hydra.system.query.ExcelImportQuery;
import com.ratel.hydra.system.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ratel.hydra.common.constant.ExceptionEnum.SYS1004;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Controller
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    @Qualifier("excelServiceAdapter")
    private ExcelService excelService;

    @RequestMapping("export")
    public void export(HttpServletResponse response, ExcelExportQuery query) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        ExcelExportDTO excelExportDTO = excelService.exportExcel(query);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            EasyExcel.write(outputStream, excelExportDTO.getCls()).sheet().doWrite(excelExportDTO.getList());
        } catch (IOException e) {
            log.error("excel 导出异常", e);
        }
    }


    @RequestMapping("import")
    public WebResult excelImport(MultipartFile excel, ExcelImportQuery query) {
        if (excel.isEmpty()){
            WebResultFactory.failed(SYS1004.getMsg(),SYS1004.toString());
        }
//        String filename = excel.getOriginalFilename();
//        String prefixName = filename.substring(0,filename.lastIndexOf("."));
//        String suffixName = filename.substring(filename.lastIndexOf("."));
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String realFileName = prefixName + uuid + suffixName;
//        File file = new File("/Users/ratel/workSpace/java/hydra");
//        ExcelTypeEnum anEnum = ExcelTypeEnum.getEnum(query.getType());
//        ExcelReaderBuilder read = EasyExcel.read(excel.getInputStream(),);
//
//        try {
//            excel.transferTo(file);
//        } catch (IOException e) {
//            log.error("文件上传异常",e);
//        }
        return WebResultFactory.ok();
    }
}
