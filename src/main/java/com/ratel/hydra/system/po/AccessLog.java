package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Null;

@ApiModel(value="com-ratel-hydra-system-po-AccessLog")
@Data
@Accessors(chain = true)
@TableName(value = "access_log")
public class AccessLog {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 访问来源IP
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="访问来源IP")
    private String ip;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 操作内容
     */
    @TableField(value = "Operation_Content")
    @ApiModelProperty(value="操作内容")
    private String operationContent;

    /**
     * 请求参数
     */
    @TableField(value = "Operation_param")
    @ApiModelProperty(value="请求参数")
    private String operationParam;

    /**
     * 访问时间
     */
    @TableField(value = "assess_time")
    @ApiModelProperty(value="访问时间")
    private Date assessTime;

    /**
     * 访问URL
     */
    @TableField(value = "assess_url")
    @ApiModelProperty(value="访问URL")
    private String assessUrl;

    /**
     * 来源网址
     */
    @TableField(value = "source_url")
    @ApiModelProperty(value="来源网址")
    private String sourceUrl;
}