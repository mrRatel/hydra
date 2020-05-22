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
    * 角色表
    */
@ApiModel(value="com-ratel-hydra-po-Role")
@Data
@TableName(value = "`role`")
public class Role {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value="角色名")
    private String roleName;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父id")
    private Long parentId;

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
}