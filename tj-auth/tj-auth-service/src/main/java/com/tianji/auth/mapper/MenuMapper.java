package com.tianji.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianji.auth.domain.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listByRoles(@Param("roleIds") List<Long> roleIds);
}
