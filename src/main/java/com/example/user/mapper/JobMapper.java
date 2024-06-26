package com.example.user.mapper;

import com.example.user.domain.Job;
import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

        Job toJob(JobRequest jobRequest);

        JobResponse toJobResponse(Job job);
}
