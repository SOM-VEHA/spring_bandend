package com.spring_bandend.spring_bandend.base;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(builderMethodName = "baseBuilder")
public class BaseResponse {

    private Boolean status;

    private Integer code;

    private String message;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime timestamp;
}