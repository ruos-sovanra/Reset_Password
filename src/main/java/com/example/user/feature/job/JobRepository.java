package com.example.user.feature.job;

import com.example.user.domain.Job;
import com.example.user.feature.job.dto.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    Page<Job> findAll(Pageable pageable);
}
