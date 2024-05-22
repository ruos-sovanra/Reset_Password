package com.example.user.feature.job;

import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;
import com.example.user.utils.CustomPage;


public interface JobService {
    JobResponse createJob(JobRequest jobRequest);
    JobResponse getJob(String id);
    JobResponse updateJob(String id, JobRequest jobRequest);
    void deleteJob(String id);
    CustomPage<JobResponse> getAllJobs(int page, int size, String baseUrl);
}
