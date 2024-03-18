package com.tianji.daily.domain.dto;


import com.tianji.common.validate.annotations.EnumValid;
import com.tianji.daily.enums.DateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(description = "每日考勤")
public class DailyDTO {
    @ApiModelProperty(value = "记录id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "姓名", example = "XXX")
    private String name;
    @ApiModelProperty(value = "工号", example = "CHNXXXXXXX")
    private String username;
    @ApiModelProperty(value = "日期", example = "2024-03-03")
    private String date;
    @ApiModelProperty(value = "下班时间", example = "9:00")
    private String ondutytime;
    @ApiModelProperty(value = "下班时间", example = "18:00")
    private String offdutytime;
    @ApiModelProperty(value = "考勤类型", example = "0")
    @EnumValid(enumeration = {0,1,2}, message = "考勤类型错误误")
    @NotNull
    private Integer datetype;
    @ApiModelProperty(value = "需求号1", example = "S2024030312345")
    private String rdmno1;
    @ApiModelProperty(value = "需求号2", example = "S2024030312345")
    private String rdmno2;
    @ApiModelProperty(value = "需求号3", example = "S2024030312345")
    private String rdmno3;
    @ApiModelProperty(value = "工作人天", example = "1")
    private Double worklength;

    @ApiModelProperty(value = "备注说明", example = "特别时段加班")
    private String note;
}
