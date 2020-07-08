package com.ratel.hydra.common.factory;

import com.ratel.hydra.common.pojo.WebResult;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class WebResultFactory {

    public static WebResult ok(Object obj,String msg){
        return new WebResult().setStatus(true).setData(obj).setMsg(msg).setCode("200");
    }

    public static WebResult ok(Object obj){
        return new WebResult().setStatus(true).setData(obj).setCode("200");
    }

    public static WebResult ok(){
        return new WebResult().setStatus(true).setCode("200");
    }


    public static WebResult failed(String msg,String code){
        return new WebResult().setStatus(false).setMsg(msg).setCode(code);
    }
    public static WebResult failed(String msg,int code,Object obj){
        return new WebResult().setStatus(false).setMsg(msg).setCode(code+"").setData(obj);
    }
}
