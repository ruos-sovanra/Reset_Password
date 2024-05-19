package com.example.user.feature.donation.dto;

import lombok.Builder;

@Builder
public record DonationRequest(
        String donationTypeId,
        Integer amount,
        String eventTypeId,
        String thumbnail,
        String userId
) {
}
