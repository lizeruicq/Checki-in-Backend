package com.tianji.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianji.auth.domain.po.LoginRecord;


public interface ILoginRecordService extends IService<LoginRecord> {

    void saveAsync(LoginRecord record);

    void loginSuccess(String cellphone, Long userId);
}
