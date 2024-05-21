package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-details")
@RequiredArgsConstructor
public class UserDetailRestController {

    private final UserDetailService userDetailService;

    @GetMapping
    @Operation(summary = "Get all user details")
    public BaseResponse<List<UserDetailResponse>> getAllUserDetails() {
        return BaseResponse.<List<UserDetailResponse>>ok()
                .setPayload(userDetailService.getAllUserDetails());
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
}
