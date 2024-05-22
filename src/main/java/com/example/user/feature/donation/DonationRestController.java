package com.example.user.feature.donation;

import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donations")
@RequiredArgsConstructor
public class DonationRestController {
    private final DonationService donationService;

    @GetMapping
    @Operation(summary = "Get all donations")
    public ResponseEntity<CustomPage<DonationResponse>> getAllDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/donations";
        CustomPage<DonationResponse> donationResponseCustomPage = donationService.getAllDonations(page, size, baseUrl);
        return ResponseEntity.ok(donationResponseCustomPage);
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
