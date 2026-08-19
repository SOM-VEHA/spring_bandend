package com.spring_bandend.spring_bandend.feature.core.role.nomalizer;

import com.spring_bandend.spring_bandend.feature.core.role.dto.request.RoleRequest;
import org.springframework.stereotype.Component;

@Component
public class RoleNormalizer {
    public RoleRequest normalize(RoleRequest roleRequest) {
        roleRequest.setName(normalizeName(roleRequest.getName()));
        roleRequest.setDescription(normalizeDescription(roleRequest.getDescription()));
        return roleRequest;
    }

    private String normalizeName(String name) {
        if(name==null || name.isEmpty()){
            return name;
        }
        return name.trim().toUpperCase();
    }
    private String normalizeDescription(String description) {
        if(description==null || description.isEmpty()){
            return description;
        }
        return description.trim().toUpperCase();
    }
}
