package com.tianji.daily.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel(description = "人员人天统计")
public class DailyPersonVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String username;

    @ApiModelProperty(value = "需求号")
    private String rdmno;

    @ApiModelProperty(value = "累计人天")
    private double sumlength;


}
