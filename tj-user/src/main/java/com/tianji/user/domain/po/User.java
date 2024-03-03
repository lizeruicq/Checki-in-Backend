package com.tianji.user.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import com.tianji.common.enums.UserType;
import com.tianji.user.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户状态：0-禁用，1-正常
     */
    private UserStatus status;

    /**
     * 用户类型：0-管理员 1-初级测试工程师, 2-中级测试工程师，3-高级测试工程师
     */
    private UserType role;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建者id
     */

    private Long creater;
    /**
     * 修改者id
     */

    private Long updater;
}
