package com.ratel.hydra.system.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-ratel-hydra-system-po-Menu")
@Data
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 菜单URL
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "菜单URL")
    private String url;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 方便查询
     */
    @TableField(value = "relation_code")
    @ApiModelProperty(value = "方便查询")
    private String relationCode;

    /**
     * 菜单名
     */
    @TableField(value = "menu_name")
    @ApiModelProperty(value = "菜单名")
    private String menuName;

    /**
     * 排序字段
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 类型 0菜单 1按钮
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "类型 0菜单 1按钮")
    private Byte type;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    @ApiModelProperty(value = "创建人")
    private Long creator;

    /**
     * 修改人
     */
    @TableField(value = "modifier")
    @ApiModelProperty(value = "修改人")
    private Long modifier;

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

    /**
     * 权限编码
     */
    @TableField(value = "permission_code")
    @ApiModelProperty(value = "权限编码")
    private String permissionCode;
}