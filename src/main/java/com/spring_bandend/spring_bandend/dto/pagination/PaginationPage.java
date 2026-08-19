package com.spring_bandend.spring_bandend.dto.pagination;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PaginationPage {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;
}
