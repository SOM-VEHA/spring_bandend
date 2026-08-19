package com.spring_bandend.spring_bandend.base;

import java.util.List;

import com.spring_bandend.spring_bandend.dto.pagination.PaginationPage;

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
public class BasePagination<T> extends BaseResponse {

    private List<T> data;

    private PaginationPage pagination;
}