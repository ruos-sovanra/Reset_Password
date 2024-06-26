package com.example.user.feature.repo;

import com.example.user.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

}
