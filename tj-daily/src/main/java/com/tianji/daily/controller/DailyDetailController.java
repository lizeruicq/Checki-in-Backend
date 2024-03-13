package com.tianji.daily.controller;


import com.tianji.common.domain.dto.PageDTO;
import com.tianji.daily.domain.query.DailyPersonRdmQuery;
import com.tianji.daily.domain.vo.DailyPersonVO;
import com.tianji.daily.domain.vo.DailyVO;
import com.tianji.daily.service.IDailyDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
@RestController
@RequestMapping("/daily-detail")
@Api(tags = "考勤详情筛选接口")
public class DailyDetailController {

    @Autowired
    private IDailyDetailService detailService;

    @ApiOperation("分页查询人员对应需求人天消费")
    @GetMapping("personlength")
    public PageDTO<DailyPersonVO> queryPersonlengthPage(DailyPersonRdmQuery pageQuery) {
        return detailService.queryPersonlengthPage(pageQuery);
    }




}
