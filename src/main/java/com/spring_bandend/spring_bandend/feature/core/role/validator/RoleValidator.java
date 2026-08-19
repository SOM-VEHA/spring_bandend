package com.spring_bandend.spring_bandend.feature.core.role.validator;
import com.spring_bandend.spring_bandend.feature.core.role.dto.request.RoleRequest;
import com.spring_bandend.spring_bandend.feature.core.role.reposiory.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class RoleValidator {
    final RoleRepository roleRepository;
    public void validate(RoleRequest roleRequest,Long id) {
        validateDuplicateName(roleRequest.getName(),id);
    }
    public void validateDuplicateName(String name,Long id){
        if (id == null && roleRepository.existsByName(name)) {
            throw new IllegalArgumentException("Role name already exists.");
        }
        if (id != null && roleRepository.existsByNameAndIdNot(name, id)) {
            throw new IllegalArgumentException("Role name already exists.");
        }
    }
}
