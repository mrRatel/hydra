package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-ratel-hydra-system-po-AccessLog")
@Data
@TableName(value = "access_log")
public class AccessLog {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 访问来源IP
     */
    @TableField(value = "ip")
    @ApiModelProperty(value = "访问来源IP")
    private String ip;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 操作内容
     */
    @TableField(value = "Operation_Content")
    @ApiModelProperty(value = "操作内容")
    private String operationContent;

    /**
     * 请求参数
     */
    @TableField(value = "Operation_param")
    @ApiModelProperty(value = "请求参数")
    private String operationParam;

    /**
     * 访问时间
     */
    @TableField(value = "access_time")
    @ApiModelProperty(value = "访问时间")
    private Date accessTime;

    /**
     * 访问URL
     */
    @TableField(value = "access_url")
    @ApiModelProperty(value = "访问URL")
    private String accessUrl;

    /**
     * 来源网址
     */
    @TableField(value = "source_url")
    @ApiModelProperty(value = "来源网址")
    private String sourceUrl;

    /**
     * ip 地区
     */
    @TableField(value = "location")
    @ApiModelProperty(value = "ip 地区")
    private String location;

    /**
     * 访问设备
     */
    @TableField(value = "access_device")
    @ApiModelProperty(value = "访问设备")
    private String accessDevice;

    /**
     * 操作系统
     */
    @TableField(value = "operating_system")
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;
}