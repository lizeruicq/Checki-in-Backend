package com.tianji.user.domain.query;

import com.tianji.common.domain.query.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户")
public class UserPageQuery extends PageQuery {
    @ApiModelProperty(value = "账户状态")
    private Integer status;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "工号")
    private String username;
}
