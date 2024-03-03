package com.tianji.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianji.auth.domain.po.AccountRole;
import com.tianji.auth.mapper.AccountRoleMapper;
import com.tianji.auth.service.IAccountRoleService;
import org.springframework.stereotype.Service;


@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements IAccountRoleService {

}
