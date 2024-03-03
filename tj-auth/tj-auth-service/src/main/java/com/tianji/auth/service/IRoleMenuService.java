package com.tianji.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.auth.domain.po.RoleMenu;

import java.util.List;


public interface IRoleMenuService extends IService<RoleMenu> {

    void removeByRoleId(Long id);

    void deleteRoleMenus(Long roleId, List<Long> menuIds);
}
