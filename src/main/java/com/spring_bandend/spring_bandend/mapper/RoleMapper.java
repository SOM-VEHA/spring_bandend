package com.spring_bandend.spring_bandend.mapper;
import com.spring_bandend.spring_bandend.entity.Role;
import com.spring_bandend.spring_bandend.feature.core.role.dto.request.RoleRequest;
import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleRequest roleRequest);
    RoleResponse toResponse(Role role);
    void updateEntity(@MappingTarget Role target, RoleRequest request);
}
