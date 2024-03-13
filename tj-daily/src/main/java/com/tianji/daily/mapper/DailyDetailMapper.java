package com.tianji.daily.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianji.daily.domain.po.DailyDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianji.daily.domain.vo.DailyPersonVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
public interface DailyDetailMapper extends BaseMapper<DailyDetail> {

    Page<DailyPersonVO> queryDailyPerson(Page<DailyPersonVO> p, @Param("ew") QueryWrapper<DailyPersonVO> wrapper);
}
