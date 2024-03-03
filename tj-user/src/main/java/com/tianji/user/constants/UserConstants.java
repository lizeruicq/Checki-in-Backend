package com.tianji.user.constants;

import java.time.Duration;

public interface UserConstants {
    String DEFAULT_PASSWORD = "Ab123456";

    Long ADMIN_ROLE_ID = 0L;
    String ADMIN_ROLE_NAME = "管理员";

    Long JUNIOR_ROLE_ID = 1L;
    String JUNIOR_ROLE_NAME = "初级测试工程师";

    Long MIDDLE_ROLE_ID = 2L;
    String MIDDLE_ROLE_NAME = "中级测试工程师";

    Long SENIOR_ROLE_ID = 3L;
    String SENIOR_ROLE_NAME = "高级测试工程师";

    // 验证码的Redis key前缀
    String USER_VERIFY_CODE_KEY = "sms:user:code:phone:";
    // 验证码有效期，5分钟
    Duration USER_VERIFY_CODE_TTL = Duration.ofMinutes(5);
}
