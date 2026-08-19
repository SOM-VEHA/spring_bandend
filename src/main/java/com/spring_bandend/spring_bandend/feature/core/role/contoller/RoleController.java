package com.spring_bandend.spring_bandend.feature.core.role.contoller;
import com.spring_bandend.spring_bandend.base.BasePagination;
import com.spring_bandend.spring_bandend.base.BaseSuccess;
import com.spring_bandend.spring_bandend.dto.pagination.PageDTO;
import com.spring_bandend.spring_bandend.dto.response.RoleImportResult;
import com.spring_bandend.spring_bandend.feature.core.role.dto.filter.RoleFilter;
import com.spring_bandend.spring_bandend.feature.core.role.dto.request.RoleRequest;
import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
import com.spring_bandend.spring_bandend.feature.core.role.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final RoleService roleService;

    RoleController(RoleService roleServiceInject) {
        this.roleService = roleServiceInject;
    }

    @PreAuthorize("hasRole('ADMIN1')")
    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest request) {
        RoleResponse role = roleService.createRole(request);
        return  ResponseEntity.ok(role);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        RoleResponse response = roleService.getRole(id);
        return ResponseEntity.ok(
                BaseSuccess.<RoleResponse>builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoleRequest request){
        RoleResponse response = roleService.updateRole(request,id);

        return ResponseEntity.ok(
                BaseSuccess.<RoleResponse>builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllFilterByName(RoleFilter filter){
        List<RoleResponse> response = roleService.getAllFilter(filter);
        return  ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<?> paginationFiler(RoleFilter roleFilter){
        Page<RoleResponse> allPagination = roleService.getAllPaginationFilter(roleFilter);
        PageDTO pageDTO = new PageDTO(allPagination);

        return  ResponseEntity.ok(
                BasePagination.<RoleResponse>builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .timestamp(LocalDateTime.now())
                        .pagination(pageDTO.getPagination())
                        .data((List<RoleResponse>) pageDTO.getData())
                        .build()
        );
    }

    @PostMapping(value = "import-xlsx", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoleImportResult> importXlsx(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(roleService.importFromXlsx(file));
    }

    @GetMapping("export-xlsx")
    public ResponseEntity<byte[]> exportXlsx() {
        byte[] xlsx = roleService.exportToXlsx();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"roles.xlsx\"")
                .body(xlsx);
    }
}
