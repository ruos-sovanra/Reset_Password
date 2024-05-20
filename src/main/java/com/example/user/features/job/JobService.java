package com.example.user.features.job;

import com.example.user.features.job.dto.JobRequest;
import com.example.user.features.job.dto.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest jobRequest);
    JobResponse getJob(String id);
    JobResponse updateJob(String id, JobRequest jobRequest);
    void deleteJob(String id);
    List<JobResponse> getAllJobs();
}
