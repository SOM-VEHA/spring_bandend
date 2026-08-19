package com.spring_bandend.spring_bandend.feature.core.role.service;
import com.spring_bandend.spring_bandend.dto.response.RoleImportResult;
import com.spring_bandend.spring_bandend.feature.core.role.dto.filter.RoleFilter;
import com.spring_bandend.spring_bandend.feature.core.role.dto.request.RoleRequest;
import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
public interface RoleService {
    Page<RoleResponse> getAllPaginationFilter(RoleFilter filter);
    List<RoleResponse> getAllFilter(RoleFilter roleFilter);
    RoleImportResult importFromXlsx(MultipartFile file);
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(RoleRequest roleRequest, Long id);
    RoleResponse getRole(Long id);
    void deleteRole(Long id);
    byte[] exportToXlsx();
}
