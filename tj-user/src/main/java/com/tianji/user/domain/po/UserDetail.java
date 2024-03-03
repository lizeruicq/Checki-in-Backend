package com.tianji.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianji.common.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_detail")
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户id
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 工号
     */
    private String username;

    /**
     * 用户类型：1-初级测试工程师, 2-中级测试工程师，3-高级测试工程师
     */
    private UserType role;

    /**
     * 名字
     */
    private String name;


    /**
     * 邮箱
     */
    private String email;



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
     * 更新者id
     */
    private Long updater;



    @TableField(exist = false)
    private String cellPhone;


    @TableField(exist = false)
    private Integer status;
}
