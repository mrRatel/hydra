package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-ratel-hydra-po-Menu")
@Data
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色id")
    private Long roleId;

    /**
     * 菜单URL
     */
    @TableField(value = "url")
    @ApiModelProperty(value="菜单URL")
    private String url;

    /**
     * 菜单图标
     */
    @TableField(value = "role_icon")
    @ApiModelProperty(value="菜单图标")
    private String roleIcon;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父id")
    private Long parentId;

    /**
     * 方便查询
     */
    @TableField(value = "relation_code")
    @ApiModelProperty(value="方便查询")
    private String relationCode;
}