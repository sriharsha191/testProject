package com.testProject.repository;

import com.testProject.entity.RoleMenuMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMappingRepository extends JpaRepository<RoleMenuMapping,Long> {

    List<RoleMenuMapping> findByRoleIdAndActiveAndCanView(Long roleId,boolean active,boolean canView);

}
