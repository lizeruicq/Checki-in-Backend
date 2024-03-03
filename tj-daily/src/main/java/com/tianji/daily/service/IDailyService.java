package com.tianji.daily.service;

import com.tianji.daily.domain.dto.DailyDTO;
import com.tianji.daily.domain.po.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
