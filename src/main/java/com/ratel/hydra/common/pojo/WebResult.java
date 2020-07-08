package com.ratel.hydra.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Data
@Accessors( chain = true)
public class WebResult <T> implements Serializable {
    /** 状态码 */
    private String code;
    /** 响应状态 */
    private boolean status;
    /** 响应内容 */
    private T data;
    /** 信息 */
    private String msg;
}
