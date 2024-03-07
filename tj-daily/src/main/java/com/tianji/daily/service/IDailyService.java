package com.tianji.daily.service;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.po.Daily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.vo.DailyVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
public interface IDailyService extends IService<Daily> {

    void saveDaily(DailyDTO dailyDTO);

    PageDTO<DailyVO> queryDailyPage(DailyPageQuery query);

    Daily queryById(int id);

    void updateDaily(DailyDTO dailyDTO);
}
