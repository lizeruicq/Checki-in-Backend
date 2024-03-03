package com.tianji.user.domain.vo;

import com.tianji.common.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "用户详情")
public class UserDetailVO {


    @ApiModelProperty(value = "用户id", example = "1")
    private Long id;
    @ApiModelProperty(value = "名字", example = "张三")
    private String name;
    @ApiModelProperty(value = "工号", example = "CHN0028042")
    private String username;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "角色", example = "中级测试工程师")
    private String role;
    @ApiModelProperty(value = "注册时间", example = "2022-07-12")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "账号状态", example = "0")
    private Integer status;

}
