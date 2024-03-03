package com.tianji.user.service;

import com.tianji.common.domain.dto.PageDTO;
import com.tianji.user.domain.query.UserPageQuery;
import com.tianji.user.domain.vo.StaffVO;


public interface IStaffService {
    PageDTO<StaffVO> queryStaffPage(UserPageQuery pageQuery);
}
