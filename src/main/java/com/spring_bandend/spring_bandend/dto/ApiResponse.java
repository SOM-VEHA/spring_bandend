package com.spring_bandend.spring_bandend.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring_bandend.spring_bandend.dto.pagination.PaginationPage;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
// @Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean status;
    private int code;
    private String message;
    private String timestamp;
    private T data;
    private PaginationPage pagination;
}
