package com.tianji.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.user.domain.po.UserDetail;
import com.tianji.user.domain.query.UserPageQuery;
import com.tianji.common.enums.UserType;

import java.util.List;


public interface IUserDetailService extends IService<UserDetail> {

    UserDetail queryById(Long userId);

    List<UserDetail> queryByIds(List<Long> ids);

    Page<UserDetail> queryUserDetailByPage(UserPageQuery pageQuery);
}
