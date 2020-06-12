package com.ratel.hydra.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class RoleVo implements Serializable {
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "description")
    private String description;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 是否生效
     */
    @ApiModelProperty(value = "是否生效")
    private Byte enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
}
