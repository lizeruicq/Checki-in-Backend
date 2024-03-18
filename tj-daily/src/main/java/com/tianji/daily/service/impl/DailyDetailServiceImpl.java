package com.tianji.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.utils.StringUtils;
import com.tianji.daily.domain.po.Daily;
import com.tianji.daily.domain.po.DailyDetail;
import com.tianji.daily.domain.query.DailyPersonRdmQuery;
import com.tianji.daily.domain.vo.DailyPersonVO;
import com.tianji.daily.mapper.DailyDetailMapper;
import com.tianji.daily.service.IDailyDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
@Service
public class DailyDetailServiceImpl extends ServiceImpl<DailyDetailMapper, DailyDetail> implements IDailyDetailService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private DailyDetailMapper detailMapper;

    @Override
    public PageDTO<DailyPersonVO> queryPersonlengthPage(DailyPersonRdmQuery query) {
        String name = query.getName();
        String rdmno = query.getRdmno();
        LocalDate startdate = LocalDate.parse("2000-01-01", formatter);
        LocalDate enddate = LocalDate.parse("2099-01-01", formatter);
        if (query.getStartdate() != null && query.getStartdate() != null)
        {
            startdate = LocalDate.parse(query.getStartdate(), formatter);
            enddate = LocalDate.parse(query.getEnddate(), formatter);
        }
        Page<DailyPersonVO> p = query.toMpPage("name",false);
        QueryWrapper<DailyPersonVO> wrapper = new QueryWrapper<>();
        wrapper
                .eq(name != null, "name", name)
                .eq(rdmno != null, "rdmno", rdmno)
                .ge(startdate != null,"date",startdate)
                .le(enddate != null,"date",enddate);
        p = getBaseMapper().queryDailyPerson(p, wrapper);
        // 4.返回

        Page<DailyPersonVO> page = detailMapper.queryDailyPerson(p, wrapper);

        return PageDTO.of(page);

    }
}












