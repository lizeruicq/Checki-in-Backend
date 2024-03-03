package com.tianji.daily.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tianji.common.constants.ErrorInfo;
import com.tianji.common.enums.BaseEnum;
import com.tianji.common.exceptions.BadRequestException;
import lombok.Getter;

@Getter
public enum DateType implements BaseEnum {
    WORKDAY(0,"工作日"),
    DAYOFF(1, "非工作日"),;

    @EnumValue
    int value;
    String desc;

    DateType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static DateType of(int value) {
        for (DateType type : DateType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new BadRequestException(ErrorInfo.Msg.INVALID_DATE_TYPE);
    }



}
