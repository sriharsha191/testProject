package com.testProject.repository;

import com.testProject.entity.MotorTypeMasters;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorTypeMasterRepository extends JpaRepository<MotorTypeMasters,Long> {

    MotorTypeMasters findByIdAndActive(Long request,boolean active);

    List<MotorTypeMasters> findByActive(boolean active);
}
