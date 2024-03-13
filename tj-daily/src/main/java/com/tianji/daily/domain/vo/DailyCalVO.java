package com.tianji.daily.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "考勤计算器")
public class DailyCalVO {

    @ApiModelProperty(value = "工号")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工作总人天")
    private Double totledays;

    @ApiModelProperty(value = "应工作人天")
    private Double standarddays;

    @ApiModelProperty(value = "结余人天")
    private Double leftdays;


}
