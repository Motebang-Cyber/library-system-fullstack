package com.example.library_webapp.repository;

import com.example.library_webapp.model.Checkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    // Existing method (keep this if you want)
    List<Checkout> findByMember_Username(String username);

    // ✅ ADD THIS METHOD (pagination support)
    Page<Checkout> findByMember_Username(String username, Pageable pageable);

    long countByMember_IdAndReturnDateIsNull(Long memberId);
}
