package com.ratel.hydra.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Data
public class ExcelExportDTO {
    public List list;
    private Class cls;
}
