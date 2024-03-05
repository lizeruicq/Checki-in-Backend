package com.tianji.daily.controller;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.vo.DailyVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tianji.daily.service.IDailyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
@RestController
@RequestMapping("/daily")
@Api(tags = "每日考勤管理接口")
public class DailyController {
    @Autowired
    private IDailyService dailyService;


    @ApiOperation("新增考勤记录")
    @PostMapping
    public void saveDaily(@Valid @RequestBody DailyDTO dailyDTO){
        dailyDTO.setId(null);
        dailyService.saveDaily(dailyDTO);

    }

    @ApiOperation("分页查询考勤信息")
    @PostMapping
    public PageDTO<DailyVO> queryDailyPage(DailyPageQuery pageQuery)
    {
        return dailyService.queryDailyPage(pageQuery);
    }






}
