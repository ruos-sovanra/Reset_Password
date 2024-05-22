package com.example.user.feature.job;

import com.example.user.feature.job.dto.JobRequest;
import com.example.user.feature.job.dto.JobResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobRestController {
    private final JobService jobService;

    @GetMapping
    @Operation(summary = "Get all jobs")
    public ResponseEntity<CustomPage<JobResponse>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/jobs";
        CustomPage<JobResponse> jobResponses = jobService.getAllJobs(page, size, baseUrl);
        return ResponseEntity.ok(jobResponses);
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
