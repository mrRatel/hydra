package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
    * 字典明细
    */
@ApiModel(value="com-ratel-hydra-system-po-DictionaryDetail")
@Data
@TableName(value = "dictionary_detail")
public class DictionaryDetail {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 表头id
     */
    @TableField(value = "pid")
    @ApiModelProperty(value="表头id")
    private Long pid;

    /**
     * 编码
     */
    @TableField(value = "code")
    @ApiModelProperty(value="编码")
    private String code;

    /**
     * 字典项名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="字典项名称")
    private String name;

    /**
     * 拓展json
     */
    @TableField(value = "external_json")
    @ApiModelProperty(value="拓展json")
    private String externalJson;

    /**
     * true 生效/false 失效
     */
    @TableField(value = "enable")
    @ApiModelProperty(value="true 生效/false 失效")
    private Boolean enable;

    /**
     * 0 优先级最高
     */
    @TableField(value = "order")
    @ApiModelProperty(value="0 优先级最高")
    private Byte order;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    @ApiModelProperty(value="修改时间")
    private Date modifyTime;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    @ApiModelProperty(value="创建人")
    private Long creator;

    /**
     * 修改人
     */
    @TableField(value = "modifier")
    @ApiModelProperty(value="修改人")
    private Long modifier;
}