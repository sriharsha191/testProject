package com.testProject.repository;

import com.testProject.entity.ControllerTypeMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerTypeMastersRepository extends JpaRepository<ControllerTypeMasters,Long> {

    ControllerTypeMasters findByIdAndActive(Long id,boolean active);

    List<ControllerTypeMasters> findByActive(boolean active);
}
