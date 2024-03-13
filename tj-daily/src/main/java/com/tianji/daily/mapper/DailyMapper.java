package com.tianji.daily.mapper;

import com.tianji.daily.domain.po.Daily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
public interface DailyMapper extends BaseMapper<Daily> {

    Daily queryById(int id);

    Double querytotaldays(String name, String username, LocalDate startdate, LocalDate enddate);

    ;
}
