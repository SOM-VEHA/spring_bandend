package com.spring_bandend.spring_bandend.dto.pagination;
import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PageDTO {
    private PaginationPage pagination;
    private List<?> data;
    public PageDTO(Page<?> page) {
        this.data = page.getContent();
        int pageNumber;
        int pageSize;
        try {
            pageSize = page.getPageable().getPageSize();
            pageNumber = page.getPageable().getPageNumber();
        } catch (UnsupportedOperationException e) {
            pageSize = page.getNumberOfElements();
            pageNumber = 1;
        }
        this.pagination = PaginationPage.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }

}