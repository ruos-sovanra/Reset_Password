package com.example.user.feature.donation.dto;

import lombok.Builder;

@Builder
public record DonationResponse(
        String id,
        String donationTypeName,
        Integer amount,
        String postTypeName,
        String eventTypeName,
        String thumbnail,
        String userName
) {
}
