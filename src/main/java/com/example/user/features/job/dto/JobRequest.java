package com.example.user.features.job.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Map;

@Builder
public record JobRequest(
        String jobTitle,
        String jobDescription,
        String jobLocation,
        String position,
        String companyName,
        BigDecimal salary,
        String userId,
        String poster,
        String statusId,
        Map<String, Object> requirements
) {
}
