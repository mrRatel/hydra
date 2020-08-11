package com.ratel.hydra.common.execption;

import com.ratel.hydra.common.constant.ExceptionEnum;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class SystemBusinessException extends BaseBusinessException {

    private SystemBusinessException() {
    }

    public SystemBusinessException(ExceptionEnum en, String msg) {
        super(en, msg);
    }

    public SystemBusinessException(ExceptionEnum en) {
        super(en,en.getMsg());
    }
}
