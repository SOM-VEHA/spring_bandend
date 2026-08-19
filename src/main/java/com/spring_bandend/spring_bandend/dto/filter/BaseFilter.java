package com.spring_bandend.spring_bandend.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseFilter implements PageSortFilter {
    private String sortBy;
    private String direction;
    private Integer page;
    private Integer size;
}
