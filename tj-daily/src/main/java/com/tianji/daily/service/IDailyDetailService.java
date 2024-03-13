package com.tianji.daily.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.daily.domain.po.DailyDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.daily.domain.query.DailyPageQuery;
import com.tianji.daily.domain.query.DailyPersonRdmQuery;
import com.tianji.daily.domain.vo.DailyPersonVO;
import com.tianji.daily.domain.vo.DailyVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rio
 * @since 2024-03-02
 */
public interface IDailyDetailService extends IService<DailyDetail> {

    PageDTO<DailyPersonVO> queryPersonlengthPage(DailyPersonRdmQuery pageQuery);
}
