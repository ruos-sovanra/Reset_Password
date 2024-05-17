package com.example.user.feature.job;

import com.example.user.domain.Job;
import com.example.user.domain.PostType;
import com.example.user.domain.Status;
import com.example.user.domain.User;
import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.repo.StatusRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final UserRepository userRepository;
    private final PostTypeRepository postTypeRepository;
    private final StatusRepository statusRepository;

    @Override
    public JobResponse createJob(JobRequest jobRequest) {
        Job job = jobMapper.toJob(jobRequest);
        User user = userRepository.findById(jobRequest.userId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        PostType postType = postTypeRepository.findByType("JOB").orElseThrow(() -> new NoSuchElementException("PostType not found"));
        Status status = statusRepository.findById(jobRequest.statusId()).orElseThrow(() -> new NoSuchElementException("Status not found"));
        job.setStatus(status);
        job.setPostType(postType);
        job.setUser(user);
        jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }

    @Override
    public JobResponse getJob(String id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Job not found"));
        return jobMapper.toJobResponse(job);
    }

    @Override
    public JobResponse updateJob(String id, JobRequest jobRequest) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Job not found"));
        Status status = statusRepository.findById(jobRequest.statusId()).orElseThrow(() -> new NoSuchElementException("Status not found"));
        User user = userRepository.findById(jobRequest.userId()).orElseThrow(() -> new NoSuchElementException("User not found"));
        PostType postType = postTypeRepository.findByType("JOB").orElseThrow(() -> new NoSuchElementException("PostType not found"));
        job.setStatus(status);
        job.setPostType(postType);
        job.setUser(user);
        job.setStatus(status);
        job.setJobDescription(jobRequest.jobDescription());
        job.setJobTitle(jobRequest.jobTitle());
        job.setJobLocation(jobRequest.jobLocation());
        job.setPosition(jobRequest.position());
        job.setCompanyName(jobRequest.companyName());
        job.setSalary(jobRequest.salary());
        job.setPoster(jobRequest.poster());
        job.setRequirements(jobRequest.requirements());
        jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }

    @Override
    public void deleteJob(String id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(jobMapper::toJobResponse)
                .toList();
    }
}
