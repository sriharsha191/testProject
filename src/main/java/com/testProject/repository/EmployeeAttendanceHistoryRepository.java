package com.testProject.repository;

import com.testProject.entity.EmployeeAttendanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeAttendanceHistoryRepository extends JpaRepository<EmployeeAttendanceHistory,Long> {

    @Query(value = "select ah from EmployeeAttendanceHistory ah where employeeEmailId=:employeeEmailId  and attendanceDate=:date and status=:status  order by createdAt desc limit 1")
    List<EmployeeAttendanceHistory> getEmployeeAttendanceHistory(@Param("employeeEmailId") String employeeEmailId, @Param("date")LocalDate date, @Param("status") boolean status);


    List<EmployeeAttendanceHistory> findByEmployeeEmailIdAndStatus(String employeeEmailId,boolean status);
}
