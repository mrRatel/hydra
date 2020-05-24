package com.ratel.hydra.system.query.user;

import com.alibaba.fastjson.JSON;
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
public class UserAdd {
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String realName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String phone;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private int sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private Integer age;

    /**
     * 注册来源
     */
    @ApiModelProperty(value="注册来源")
    private String registerFrom;

    public static void main(String[] args) {
        UserAdd userAdd = new UserAdd();
        userAdd.setUsername("");
        userAdd.setPassword("");
        userAdd.setRealName("");
        userAdd.setEmail("");
        userAdd.setPhone("");
        userAdd.setSex(1);
        userAdd.setAge(0);
        userAdd.setRegisterFrom("");
        System.out.println(JSON.toJSONString(userAdd));
    }
}
