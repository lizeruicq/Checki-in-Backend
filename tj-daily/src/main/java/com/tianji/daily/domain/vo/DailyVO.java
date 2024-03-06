package com.tianji.daily.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.tianji.daily.enums.DateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "考勤详情")
public class DailyVO {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "登记日期")
    private LocalDate date;

    @ApiModelProperty(value = "考勤类别")
    private DateType datetype;

    @ApiModelProperty(value = "上班时间")
    private String ondutytime;

    @ApiModelProperty(value = "下班时间")
    private String offdutytime;

    @ApiModelProperty(value = "工号")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "需求号1")
    private String rdmno1;

    @ApiModelProperty(value = "需求号2")
    private String rdmno2;

    @ApiModelProperty(value = "需求号3")
    private String rdmno3;

    @ApiModelProperty(value = "工作人天")
    private Double worklength;
}
