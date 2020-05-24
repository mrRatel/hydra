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
    * 用户表
    */
@ApiModel(value="com-ratel-hydra-po-User")
@Data
@TableName(value = "`user`")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    @ApiModelProperty(value="真实姓名")
    private String realName;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 电话
     */
    @TableField(value = "phone")
    @ApiModelProperty(value="电话")
    private String phone;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value="性别")
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value="年龄")
    private Integer age;

    /**
     * 注册来源
     */
    @TableField(value = "register_from")
    @ApiModelProperty(value="注册来源")
    private String registerFrom;

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