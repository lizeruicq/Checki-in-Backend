package com.tianji.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.auth.domain.po.Menu;

import java.util.List;


public interface IMenuService extends IService<Menu> {

    List<Menu> listMenuByUser();

    void saveMenu(Menu menu);

    void deleteMenu(Long id);

    void bindRoleMenus(Long roleId, List<Long> menuIds);

    void deleteRoleMenus(Long roleId, List<Long> menuIds);
}
