package com.testProject.repository;

import com.testProject.entity.LanguageMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageMastersRepository extends JpaRepository<LanguageMasters,Long> {

    List<LanguageMasters> findByStatus(boolean status);

    LanguageMasters findById(long languageId);
}
