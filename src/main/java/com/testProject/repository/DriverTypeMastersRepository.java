package com.testProject.repository;

import com.testProject.entity.DriverTypeMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverTypeMastersRepository extends JpaRepository<DriverTypeMasters,Long> {

    DriverTypeMasters findByIdAndActive(Long driverTypeId, boolean active);

    List<DriverTypeMasters> findByActive(boolean active);
}
