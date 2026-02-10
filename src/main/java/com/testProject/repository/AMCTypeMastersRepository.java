package com.testProject.repository;

import com.testProject.entity.AMCTypeMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AMCTypeMastersRepository extends JpaRepository<AMCTypeMasters,Long> {

    AMCTypeMasters findByIdAndActive(Long id,boolean active);

    List<AMCTypeMasters> findByActive(boolean active);
}
