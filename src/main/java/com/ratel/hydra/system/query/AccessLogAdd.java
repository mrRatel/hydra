package com.ratel.hydra.system.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Data
@Accessors(chain = true)
public class AccessLogAdd {
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 访问来源IP
     */
    @ApiModelProperty(value="访问来源IP")
    private String ip;

    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 操作内容
     */
    @ApiModelProperty(value="操作内容")
    private String operationContent;

    /**
     * 请求参数
     */
    @ApiModelProperty(value="请求参数")
    private String operationParam;

    /**
     * 访问时间
     */
    @ApiModelProperty(value="访问时间")
    private Date assessTime;

    /**
     * 访问URL
     */
    @ApiModelProperty(value="访问URL")
    private String assessUrl;

    /**
     * 来源网址
     */
    @ApiModelProperty(value="来源网址")
    private String sourceUrl;

}
