package com.tianji.daily.domain.query;


import com.tianji.common.domain.query.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@Data
@ApiModel(description = "总人天计算器查询")
public class DailyCalQuery  {

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "工号")
    private String username;
    @ApiModelProperty(value = "开始日期")
    private LocalDate startdate;
    @ApiModelProperty(value = "结束日期")
    private LocalDate enddate;
    @ApiModelProperty(value = "非工作日天数")
    private Integer dayoffnums ;
}
