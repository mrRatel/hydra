package com.ratel.hydra.common.vo;

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
    private T Content;
    /** 信息 */
    private String msg;
}
