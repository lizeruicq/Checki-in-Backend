package com.tianji.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.auth.domain.po.RolePrivilege;

import java.util.List;


public interface IRolePrivilegeService extends IService<RolePrivilege> {

    void removeByPrivilegeId(Long id);

    void removeByRoleId(Long id);

    void deleteRolePrivileges(Long roleId, List<Long> privilegeIds);
}
