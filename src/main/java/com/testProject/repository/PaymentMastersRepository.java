package com.testProject.repository;

import com.testProject.entity.PaymentMasters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMastersRepository extends JpaRepository<PaymentMasters,Long> {

    PaymentMasters findByIdAndActive(Long id,boolean active);

    List<PaymentMasters> findByActive(boolean active);
}
