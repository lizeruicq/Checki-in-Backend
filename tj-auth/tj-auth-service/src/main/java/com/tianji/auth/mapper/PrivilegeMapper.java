package com.tianji.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianji.auth.domain.po.Privilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PrivilegeMapper extends BaseMapper<Privilege> {

    List<Privilege> listRolePrivileges(@Param("roleId") Long roleId);
}
