package com.tianji.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianji.common.enums.UserType;
import com.tianji.common.utils.StringUtils;
import com.tianji.user.domain.po.UserDetail;
import com.tianji.user.domain.query.UserPageQuery;
import com.tianji.user.mapper.UserDetailMapper;
import com.tianji.user.service.IUserDetailService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService {

    @Override
    public UserDetail queryById(Long userId) {
        return getBaseMapper().queryById(userId);
    }

    @Override
    public List<UserDetail> queryByIds(List<Long> ids) {
        return getBaseMapper().queryByIds(ids);
    }

    @Override
    public Page<UserDetail> queryUserDetailByPage(UserPageQuery query) {
        // 1.分页条件
        Page<UserDetail> p = query.toMpPageDefaultSortByCreateTimeDesc();
        // 2.搜索条件
        Integer status = query.getStatus();
        String name = query.getName();
        String username = query.getUsername();
        QueryWrapper<UserDetail> wrapper = new QueryWrapper<>();
        wrapper
//                .eq(role != null , "u.type", role)
//                .eq(status != null, "u.status", status)
                .eq(username != null, "u.username", username)
                .like(StringUtils.isNotBlank(name), "ud.name", name);
        // 3.查询
        p = getBaseMapper().queryByPage(p, wrapper);
        // 4.返回
        return p;
    }
}
