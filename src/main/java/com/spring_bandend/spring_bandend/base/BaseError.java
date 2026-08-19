package com.spring_bandend.spring_bandend.base;
import com.spring_bandend.spring_bandend.base.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BaseError<T> extends  BaseResponse {
    private T errors;
    public static <T> BaseError<T> of(Integer code, String message, T errors) {
        return BaseError.<T>builder()
                .status(false)
                .code(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }
}