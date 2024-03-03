package com.tianji.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.api.dto.user.LoginFormDTO;
import com.tianji.api.dto.user.UserDTO;
import com.tianji.common.domain.dto.LoginUserDTO;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.user.domain.dto.UserFormDTO;
import com.tianji.user.domain.po.User;
import com.tianji.user.domain.query.UserPageQuery;
import com.tianji.user.domain.vo.StaffVO;
import com.tianji.user.domain.vo.UserDetailVO;

/**
 * <p>
 * 学员用户表 服务类
 * </p>
 *
 */
public interface IUserService extends IService<User> {
    LoginUserDTO queryUserDetail(LoginFormDTO loginDTO);

    void resetPassword(Long userId);

    UserDetailVO myInfo();

//    void addUserByPhone(User user, String code);
//
//    void updatePasswordByPhone(String cellPhone, String code, String password);

    Long saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void updateUserWithPassword(UserFormDTO userDTO);

    PageDTO<UserDetailVO> queryUserPage(UserPageQuery query);
}
