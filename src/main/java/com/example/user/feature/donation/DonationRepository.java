package com.example.user.feature.donation;

import com.example.user.domain.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {

    Page<Donation> findAll(Pageable pageable);
}
