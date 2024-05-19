package com.example.user.feature.donation;

import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;

import java.util.List;

public interface DonationService {
    List<DonationResponse> getAllDonations();
    DonationResponse getDonationById(String id);
    DonationResponse createDonation(DonationRequest donationRequest);
    DonationResponse updateDonation(String id, DonationRequest donationRequest);
    void deleteDonation(String id);
}
