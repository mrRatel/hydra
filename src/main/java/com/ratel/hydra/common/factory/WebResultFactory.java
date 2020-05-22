package com.ratel.hydra.common.factory;

import com.ratel.hydra.common.vo.WebResult;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class WebResultFactory {

    public static WebResult ok(Object obj,String msg){
        return new WebResult().setStatus(true).setContent(obj).setMsg(msg);
    }


    public static WebResult failed(String msg,String code){
        return new WebResult().setStatus(false).setMsg(msg).setCode(code);
    }
}
