package com.testProject.repository;

import com.testProject.entity.MenuMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMasterRepository extends JpaRepository<MenuMaster,Long> {

    MenuMaster findByIdAndActive(long id,boolean active);
}
