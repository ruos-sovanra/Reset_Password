package com.example.user.feature.donation;

import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;
import com.example.user.utils.CustomPage;

import java.util.List;

public interface DonationService {
    CustomPage<DonationResponse> getAllDonations(int page, int size, String baseUrl);
    DonationResponse getDonationById(String id);
    DonationResponse createDonation(DonationRequest donationRequest);
    DonationResponse updateDonation(String id, DonationRequest donationRequest);
    void deleteDonation(String id);
}
