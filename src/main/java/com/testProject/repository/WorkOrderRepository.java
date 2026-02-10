package com.testProject.repository;

import com.testProject.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {

    WorkOrder findById(long id);
}
