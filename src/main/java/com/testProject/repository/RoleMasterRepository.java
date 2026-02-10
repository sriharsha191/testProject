package com.testProject.repository;

import com.testProject.entity.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster,Long> {

    RoleMaster findById(long roleId);


    RoleMaster findByIdAndActive(Long roleId,boolean active);
}

