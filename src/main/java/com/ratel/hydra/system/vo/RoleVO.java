package com.ratel.hydra.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ratel
 * @date 2020-06-12
 */
@Data
@Accessors(chain = true)
@TableName(value = "`role`")
public class RoleVO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "description")
    @ApiModelProperty(value = "")
    private String description;

    /**
     * 角色编码
     */
    @TableField(value = "role_code")
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 是否生效
     */
    @TableField(value = "enable")
    @ApiModelProperty(value = "是否生效")
    private Boolean enable;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    /** 当前用户是否拥有 */
    @TableField(value = "have")
    private boolean have;
}
