package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="com-ratel-hydra-system-po-LoginLog")
@Data
@TableName(value = "login_log")
public class LoginLog implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    @ApiModelProperty(value="登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    private Date loginTime;

    /**
     * 登录地址
     */
    @TableField(value = "location")
    @ApiModelProperty(value="登录地址")
    private String location;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="ip地址")
    private String ip;

    /**
     * 登录设备
     */
    @TableField(value = "device")
    @ApiModelProperty(value="登录设备")
    private String device;

    /**
     * 浏览器
     */
    @TableField(value = "brower")
    @ApiModelProperty(value="浏览器")
    private String brower;
}