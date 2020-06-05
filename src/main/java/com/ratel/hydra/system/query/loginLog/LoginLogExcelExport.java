package com.ratel.hydra.system.query.loginLog;

import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.query.ExcelExportQuery;
import lombok.Data;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Data
public class LoginLogExcelExport extends ExcelExportQuery {
    private LoginLog loginLog;
}
