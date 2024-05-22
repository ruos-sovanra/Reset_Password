package com.example.user.feature.employ;

import com.example.user.domain.EmployType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployTypeRepository extends JpaRepository<EmployType, String> {

    EmployType findByEmployType(String employType);

    Page<EmployType> findAll(Pageable pageable);
}
