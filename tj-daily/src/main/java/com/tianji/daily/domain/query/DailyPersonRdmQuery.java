package com.tianji.daily.domain.query;


import com.tianji.common.domain.query.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "人员需求消耗总人天查询")
public class DailyPersonRdmQuery extends PageQuery {

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "需求号")
    private String rdmno;
    @ApiModelProperty(value = "开始日期")
    private String startdate;
    @ApiModelProperty(value = "结束日期")
    private String enddate;
}
