package com.tianji.common.utils;

import com.tianji.common.exceptions.BadRequestException;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;


public class ViolationUtils {

    public static <T> void process(Set<ConstraintViolation<T>> violations) {
        if(CollUtils.isEmpty(violations)){
            return;
        }
        String message = violations.stream().map(v -> v.getMessage()).collect(Collectors.joining("|"));
        throw new BadRequestException(message);
    }
}
