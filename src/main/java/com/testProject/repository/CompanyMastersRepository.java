package com.testProject.repository;

import com.testProject.entity.CompanyMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMastersRepository extends JpaRepository<CompanyMasters,Long> {

    CompanyMasters findByIdAndActive(Long companyId,boolean active);

    List<CompanyMasters> findByActive(boolean active);

    CompanyMasters findById(long companyId);

    CompanyMasters findByCompanyNameAndActive(String companyName,boolean active);


    CompanyMasters findByCompanyNameAndAdminEmailIdAndActive(String companyName,String adminEmailId,boolean active);


    CompanyMasters findByAdminEmailIdAndActive(String adminEmailId,boolean active);

    CompanyMasters findByAdminEmailId(String adminEmailId);
}
