package com.ratel.hydra.common.execption;

import com.ratel.hydra.common.constant.ExceptionEnum;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class SystemException extends BaseException {

    private SystemException() {
    }

    public SystemException(ExceptionEnum en, String msg) {
        super(en, msg);
    }

    public SystemException(ExceptionEnum en) {
        super(en,en.getMsg());
    }
}
