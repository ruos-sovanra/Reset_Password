package com.example.user.feature.job;

import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest jobRequest);
    JobResponse getJob(String id);
    JobResponse updateJob(String id, JobRequest jobRequest);
    void deleteJob(String id);
    List<JobResponse> getAllJobs();
}
