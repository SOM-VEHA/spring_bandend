package com.spring_bandend.spring_bandend.base;
import com.spring_bandend.spring_bandend.base.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BaseSuccess<T> extends BaseResponse {
    private T data;
}