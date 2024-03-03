package com.tianji.auth.service;

import com.tianji.api.dto.user.LoginFormDTO;


public interface IAccountService{

    String login(LoginFormDTO loginFormDTO);

    void logout();

    String refreshToken(String refreshToken);
}
