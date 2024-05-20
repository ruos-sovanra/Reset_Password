package com.example.user.features.donation;

import com.example.user.features.donation.dto.DonationRequest;
import com.example.user.features.donation.dto.DonationResponse;

import java.util.List;

public interface DonationService {
    List<DonationResponse> getAllDonations();
    DonationResponse getDonationById(String id);
    DonationResponse createDonation(DonationRequest donationRequest);
    DonationResponse updateDonation(String id, DonationRequest donationRequest);
    void deleteDonation(String id);
}
