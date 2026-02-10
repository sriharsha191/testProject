package com.testProject.repository;

import com.testProject.entity.SitesMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitesMasterRepository extends JpaRepository<SitesMasters,Long> {


    List<SitesMasters> findByActive(boolean active);

    List<SitesMasters> findByAssociatedCompanyIdAndActive(Long associatedCompanyId,boolean active);


    SitesMasters findByApartmentNameAndActive(String apartmentName,boolean active);

    SitesMasters findById(long id);
}
