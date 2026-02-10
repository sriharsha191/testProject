package com.testProject.repository;

import com.testProject.entity.ButtonModelMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ButtonModelMastersRepository extends JpaRepository<ButtonModelMasters,Long> {

    ButtonModelMasters findByIdAndActive(Long id,boolean active);

    List<ButtonModelMasters> findByActive(boolean active);
}
