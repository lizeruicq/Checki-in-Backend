package com.tianji.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianji.api.client.auth.AuthClient;
import com.tianji.api.dto.auth.RoleDTO;
import com.tianji.api.dto.user.LoginFormDTO;
import com.tianji.api.dto.user.UserDTO;
import com.tianji.common.domain.dto.LoginUserDTO;
import com.tianji.common.domain.dto.PageDTO;
import com.tianji.common.enums.UserType;
import com.tianji.common.exceptions.BadRequestException;
import com.tianji.common.exceptions.ForbiddenException;
import com.tianji.common.exceptions.UnauthorizedException;
import com.tianji.common.utils.AssertUtils;
import com.tianji.common.utils.BeanUtils;
import com.tianji.common.utils.StringUtils;
import com.tianji.common.utils.UserContext;
import com.tianji.user.domain.dto.UserFormDTO;
import com.tianji.user.domain.po.User;
import com.tianji.user.domain.po.UserDetail;
import com.tianji.user.domain.query.UserPageQuery;
import com.tianji.user.domain.vo.StaffVO;
import com.tianji.user.domain.vo.UserDetailVO;
import com.tianji.user.enums.UserStatus;
import com.tianji.user.mapper.UserMapper;
import com.tianji.user.service.IUserDetailService;
import com.tianji.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tianji.user.constants.UserConstants.*;
import static com.tianji.user.constants.UserErrorInfo.Msg.*;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthClient authClient;
    @Autowired
    private IUserDetailService detailService;

    @Override
    public LoginUserDTO queryUserDetail(LoginFormDTO loginDTO) {
        System.out.println(loginDTO);
        // 1.判断登录方式
        Integer type = loginDTO.getType();
        User user = null;
        // 2.用户名和密码登录
        if (type == 1) {
            user = loginByPw(loginDTO);
        }
        // 3.验证码登录
//        if (type == 2) {
//            user = loginByVerifyCode(loginDTO.getCellPhone(), loginDTO.getPassword());
//        }
        // 4.错误的登录方式
        if (user == null) {
            throw new BadRequestException(ILLEGAL_LOGIN_TYPE);
        }

        // 5.封装返回
        LoginUserDTO userDTO = new LoginUserDTO();
        userDTO.setUserId(user.getId());
//        userDTO.setRoleId(handleRoleId(user));
        return userDTO;
    }

    @Override
    public void resetPassword(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setPassword(DEFAULT_PASSWORD);
        AssertUtils.isTrue(updateById(user), USER_ID_NOT_EXISTS);
    }

    @Override
    public UserDetailVO myInfo() {
        // 1.获取登录用户id
        Long userId = UserContext.getUser();
        if (userId == null) {
            return null;
        }
        System.out.println("当前的USERID为："+userId.toString());
        // 2.查询用户
        UserDetail userDetail = detailService.queryById(userId);
        AssertUtils.isNotNull(userDetail, USER_ID_NOT_EXISTS);
        // 3.封装vo
        UserType role = userDetail.getRole();
        // 3.1.基本信息
        UserDetailVO vo = BeanUtils.toBean(userDetail, UserDetailVO.class);
        // 3.2.详情信息
        switch (role) {
            case ADMIN:
                vo.setRole(ADMIN_ROLE_NAME);
                break;
            case JUNIOR:
                vo.setRole(JUNIOR_ROLE_NAME);
                break;
            case MIDDLE:
                vo.setRole(MIDDLE_ROLE_NAME);
                break;
            case SENIOR:
                vo.setRole(SENIOR_ROLE_NAME);
                break;
            default:
                break;
        }
        return vo;
    }



    public void updatePhoneById(Long id, String cellPhone) {
        // 1.1.判断是否需要修改手机号
        if (StringUtils.isNotBlank(cellPhone)) {
            // 1.2.需要修改，封装数据
            User user = new User();
            user.setId(id);
            user.setUsername(cellPhone);
            // 1.3.修改
            updateById(user);
        }
    }

    @Override
    @Transactional
    public Long saveUser(UserDTO userDTO) {
        UserType role = UserType.of(userDTO.getRole());
        // 1.保存用户基本信息
        User user = new User();
//        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setPassword(DEFAULT_PASSWORD);
        user.setUsername(userDTO.getUsername());
        user.setRole(role);
        save(user);
        // 2.新增详情
        UserDetail detail = BeanUtils.toBean(userDTO, UserDetail.class);
        detail.setId(user.getId());
        detail.setRole(role);
//        if(type == UserType.TEACHER){
//            detail.setRoleId(TEACHER_ROLE_ID);
//        }else{
//            if (userDTO.getRoleId() == null) {
//                throw new BadRequestException("员工角色信息不能为空");
//            }
//        }
        detailService.save(detail);
        return user.getId();
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        UserType role = UserType.of(userDTO.getRole());
        //1.修改用户信息
        User user = new User();
        user.setId(userDTO.getId());
        user.setRole(role);
        user.setUsername(userDTO.getUsername());
        updateById(user);
        // 2.修改详情
        UserDetail detail = BeanUtils.toBean(userDTO, UserDetail.class);
        detailService.updateById(detail);
    }

    @Override
    public void updateUserWithPassword(UserFormDTO userDTO) {
        // 1.尝试更新密码
//        String pw = userDTO.getPassword();
//        String oldPw = userDTO.getOldPassword();
//        if(StringUtils.isNotBlank(pw) && StringUtils.isNotBlank(pw)) {
//            Long userId = UserContext.getUser();
//            // 1.1.查询用户
//            User user = getById(userId);
//            // 1.2.校验
//            if (user == null) {
//                throw new UnauthorizedException(USER_ID_NOT_EXISTS);
//            }
//            // 1.3.校验密码
//            if (!passwordEncoder.matches(oldPw, user.getPassword())) {
//                // 密码不一致
//                throw new UnauthorizedException(INVALID_UN_OR_PW);
//            }
//            // 1.4.修改密码
//            user = new User();
//            user.setId(userId);
//            user.setPassword(passwordEncoder.encode(pw));
//            updateById(user);
//        }
//        // 2.更新用户详情
//        UserDetail detail = BeanUtils.toBean(userDTO, UserDetail.class);
//        detail.setRoleId(null);
//        detail.setType(null);
//        detailService.updateById(detail);
    }

    @Override
    public PageDTO<UserDetailVO> queryUserPage(UserPageQuery query) {
        // 1.搜索
//        Page<UserDetail> p = detailService.queryUserDetailByPage(query, UserType.STAFF);
        Page<UserDetail> p = detailService.queryUserDetailByPage(query);

        // 2.处理vo
        return PageDTO.of(p, u -> {
            UserType role = u.getRole();
            UserDetailVO v = BeanUtils.toBean(u, UserDetailVO.class);
//            v.setRoleName(roleCache.getRoleName(u.getRoleId()));
            switch (role) {
                case ADMIN:
                    v.setRole(ADMIN_ROLE_NAME);
                    break;
                case JUNIOR:
                    v.setRole(JUNIOR_ROLE_NAME);
                    break;
                case MIDDLE:
                    v.setRole(MIDDLE_ROLE_NAME);
                    break;
                case SENIOR:
                    v.setRole(SENIOR_ROLE_NAME);
                    break;
                default:
                    break;
            }
            return v;
        });
    }

    public User loginByPw(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUsername();
        String cellPhone = loginDTO.getCellPhone();
        if (StrUtil.isBlank(username) && StrUtil.isBlank(cellPhone)) {
            throw new BadRequestException(INVALID_UN);
        }
        // 2.根据用户名或手机号查询
        User user = lambdaQuery()
                .eq(StrUtil.isNotBlank(username), User::getUsername, username)
                .one();
        AssertUtils.isNotNull(user, INVALID_UN_OR_PW);
        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException(USER_FROZEN);
        }
        // 4.校验密码
        System.out.println(loginDTO.getPassword()+"     "+ user.getPassword());
        if (!loginDTO.getPassword().equals(user.getPassword())) {
//        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException(INVALID_UN_OR_PW);
        }

        return user;
    }

    private Long handleRoleId(User user) {
//        Long roleId = 0L;
////        switch (user.getType()) {
////            case STUDENT:
////                roleId = STUDENT_ROLE_ID;
////                break;
////            case TEACHER:
////                roleId = TEACHER_ROLE_ID;
////                break;
////            case STAFF:
////                UserDetail detail = detailService.getById(user.getId());
////                roleId = detail.getRoleId();
////                break;
////        }
////        return roleId;
        return null;
    }

//    public User loginByVerifyCode(String phone, String code) {
//        // 1.校验验证码
//        codeService.verifyCode(phone, code);
//        // 2.根据手机号查询
//        User user = lambdaQuery().eq(User::getCellPhone, phone).one();
//        if (user == null) {
//            throw new BadRequestException(PHONE_NOT_EXISTS);
//        }
//        // 3.校验是否禁用
//        if (user.getStatus() == UserStatus.FROZEN) {
//            throw new ForbiddenException(USER_FROZEN);
//        }
//        return user;
//    }
}
