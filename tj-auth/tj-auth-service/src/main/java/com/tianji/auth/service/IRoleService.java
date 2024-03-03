package com.tianji.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.auth.domain.po.Role;

import java.util.List;


public interface IRoleService extends IService<Role> {

    boolean exists(Long roleId);
    boolean exists(List<Long> roleIds);

    void deleteRole(Long id);
}
