package com.tianji.daily.controller;

import com.tianji.api.dto.user.UserDTO;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.utils.BeanUtils;
import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.po.Daily;
import com.tianji.daily.domain.query.DailyCalQuery;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.vo.DailyCalVO;
import com.tianji.daily.domain.vo.DailyVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("daily")
@Api(tags = "每日考勤管理接口")
public class DailyController {
    @Autowired
    private IDailyService dailyService;


    @ApiOperation("新增考勤记录")
    @PostMapping
    public void saveDaily(@Valid @RequestBody DailyDTO dailyDTO) {
        dailyDTO.setId(null);
        dailyService.saveDaily(dailyDTO);

    }

    @ApiOperation("分页查询考勤信息")
    @GetMapping("page")
    public PageDTO<DailyVO> queryDailyPage(DailyPageQuery pageQuery) {
        return dailyService.queryDailyPage(pageQuery);
    }

    @ApiOperation("考勤总人天计算器")
    @GetMapping("cal")
    public DailyCalVO DailyCalculator(DailyCalQuery dailyCalQuery) {
        return dailyService.DailyCalculator(dailyCalQuery);
    }


    @ApiOperation("根据id查询考勤信息")
    @GetMapping("/{id}")
    public DailyDTO queryUserById(@ApiParam("考勤id") @PathVariable("id") int id) {
        Daily daily = dailyService.queryById(id);
        return BeanUtils.copyBean(daily, DailyDTO.class, (d, u) -> u.setDatetype(d.getDatetype().getValue()));

    }

    @ApiOperation("更新考勤信息")
    @PutMapping("/{id}")
    public void updateDaily(@RequestBody DailyDTO dailyDTO){
        dailyService.updateDaily(dailyDTO);
    }
}




