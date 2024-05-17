package com.example.user.feature.job;

import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobRestController {
    private final JobService jobService;

    @GetMapping
    @Operation(summary = "Get all jobs")
    public BaseResponse<List<JobResponse>> getAllJobs() {
        return BaseResponse.<List<JobResponse>>ok()
                .setPayload(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get job by id")
    public BaseResponse<JobResponse> getJob(String id) {
        return BaseResponse.<JobResponse>ok()
                .setPayload(jobService.getJob(id));
    }

    @PostMapping
    @Operation(summary = "Create job")
    public BaseResponse<JobResponse> createJob(@Valid @RequestBody JobRequest jobRequest) {
        return BaseResponse.<JobResponse>createSuccess()
                .setPayload(jobService.createJob(jobRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update job")
    public BaseResponse<JobResponse> updateJob(@PathVariable String id, @Valid @RequestBody JobRequest jobRequest) {
        return BaseResponse.<JobResponse>updateSuccess()
                .setPayload(jobService.updateJob(id, jobRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete job")
    public BaseResponse<Void> deleteJob(@PathVariable String id) {
        jobService.deleteJob(id);
        return BaseResponse.deleteSuccess();
    }

}
