package com.example.user.features.donation;

import com.example.user.features.donation.dto.DonationRequest;
import com.example.user.features.donation.dto.DonationResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donations")
@RequiredArgsConstructor
public class DonationRestController {
    private final DonationService donationService;

    @GetMapping
    @Operation(summary = "Get all donations")
    public BaseResponse<List<DonationResponse>> getAllDonations() {
        return BaseResponse.<List<DonationResponse>>ok()
                .setPayload(donationService.getAllDonations());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get donation by id")
    public BaseResponse<DonationResponse> getDonationById(String id) {
        return BaseResponse.<DonationResponse>ok()
                .setPayload(donationService.getDonationById(id));
    }

    @PostMapping
@Operation(summary = "Create donation")
    public BaseResponse<DonationResponse> createDonation(DonationRequest donationRequest) {
        return BaseResponse.<DonationResponse>createSuccess()
                .setPayload(donationService.createDonation(donationRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update donation")
    public BaseResponse<DonationResponse> updateDonation(String id, DonationRequest donationRequest) {
        return BaseResponse.<DonationResponse>updateSuccess()
                .setPayload(donationService.updateDonation(id, donationRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete donation")
    public BaseResponse<Void> deleteDonation(String id) {
        donationService.deleteDonation(id);
        return BaseResponse.<Void>deleteSuccess();
    }

}
