package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.AdminUpdateRequest;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-details")
@RequiredArgsConstructor
public class UserDetailRestController {

    private final UserDetailService userDetailService;

    @GetMapping
    @Operation(summary = "Get all user details")
    public ResponseEntity<CustomPage<UserDetailResponse>> getAllUserDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
        String baseUrl =request.getScheme()+ "://"+ request.getServerName() + ":" + request.getServerPort() +  "/api/v1/user-details";
        CustomPage<UserDetailResponse> userDetailResponseCustomPage = userDetailService.getAllUserDetails(page, size, baseUrl);
        return ResponseEntity.ok(userDetailResponseCustomPage);
    }

    @PostMapping
    @Operation(summary = "Create user detail")
    public BaseResponse<UserDetailResponse> createUserDetail(@RequestBody UserDetailRequest userDetailRequest) {
        return BaseResponse.<UserDetailResponse>createSuccess()
                .setPayload(userDetailService.createUserDetail(userDetailRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user detail")
    public BaseResponse<UserDetailResponse> updateUserDetail(@PathVariable String id,@RequestBody UserDetailRequest userDetailRequest) {
        return BaseResponse.<UserDetailResponse>updateSuccess()
                .setPayload(userDetailService.updateUserDetail(id, userDetailRequest));
    }

    @PutMapping("/admin/{id}")
    @Operation(summary = "Admin update user detail")
    public BaseResponse<UserDetailResponse> adminUpdateUserDetail(@PathVariable String id,@RequestBody AdminUpdateRequest adminUpdateRequest) {
        return BaseResponse.<UserDetailResponse>updateSuccess()
                .setPayload(userDetailService.adminUpdateUserDetail(id, adminUpdateRequest));
    }
}
