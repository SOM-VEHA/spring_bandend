package com.spring_bandend.spring_bandend.dto.response;
import java.util.List;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class RoleImportResult {
    private int totalRows;
    private int imported;
    private int skipped;
    private List<String> errors;
}
