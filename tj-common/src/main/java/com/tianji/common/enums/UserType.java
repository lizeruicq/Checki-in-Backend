package com.tianji.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tianji.common.constants.ErrorInfo;
import com.tianji.common.exceptions.BadRequestException;
import lombok.Getter;

@Getter
public enum UserType implements BaseEnum{
    ADMIN(0,"管理员"),
    JUNIOR(1, "初级测试工程师"),
    MIDDLE(2, "中级测试工程师"),
    SENIOR(3, "高级测试工程师"),
    ;
    @EnumValue
    int value;
    String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserType of(int value) {
        for (UserType type : UserType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_USER_TYPE);
    }
}
