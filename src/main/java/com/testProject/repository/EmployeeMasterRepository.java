package com.testProject.repository;

import com.testProject.entity.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster,Long> {

    EmployeeMaster findByEmailIdAndActive(String emailId,boolean active);

    EmployeeMaster findByEmailId(String emailId);


    EmployeeMaster findById(long employeeId);


    List<EmployeeMaster> findByCompanyIdAndActive(Long companyId,boolean active);
}
