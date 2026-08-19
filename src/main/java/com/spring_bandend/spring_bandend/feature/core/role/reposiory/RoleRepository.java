package com.spring_bandend.spring_bandend.feature.core.role.reposiory;
import com.spring_bandend.spring_bandend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}