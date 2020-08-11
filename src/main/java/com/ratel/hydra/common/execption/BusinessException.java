package com.ratel.hydra.common.execption;

import com.ratel.hydra.common.constant.ExceptionEnum;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class BusinessException extends BaseRunTimeException {

    private BusinessException() {
    }

    public BusinessException(ExceptionEnum en, String msg) {
        super(en, msg);
    }

    public BusinessException(ExceptionEnum en) {
        super(en,en.getMsg());
    }
}
