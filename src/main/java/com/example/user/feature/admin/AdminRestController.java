package com.example.user.feature.admin;


import com.example.user.feature.user.UserService;
import com.example.user.feature.user.dto.CreateUserRequest;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.feature.user.dto.ProfileUpdateRequest;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<CustomPage<UserResponse>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/admin";
        CustomPage<UserResponse> userResponseCustomPage = userService.getAllUsers(page, size, baseUrl);
        return ResponseEntity.ok(userResponseCustomPage);
    }

    @PatchMapping("/{id}/profile")
    @Operation(summary = "Update user profile")
    public BaseResponse<UserResponse> updateProfile(@PathVariable String id, @RequestBody ProfileUpdateRequest userRequest){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.updateProfile(id, userRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public BaseResponse<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return BaseResponse.<Void>deleteSuccess();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById(String id){
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @GetMapping("/verify")
    @Operation(summary = "Get all users by is verify")
    public BaseResponse<List<UserResponse>> getAllUsersByIsVerify(){
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsersByIsVerify());
    }


    @PatchMapping("/{id}/verify")
    @Operation(summary = "Verify user")
    public BaseResponse<UserResponse> isVerified(@PathVariable String id){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.isVerified(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create user")
    public BaseResponse<UserResponse> createUsers(@Valid @RequestBody CreateUserRequest userRequest){
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUsers(userRequest));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable user")
    public BaseResponse<UserResponse> isDisabled(@PathVariable String id){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.isDisabled(id));
    }

}
