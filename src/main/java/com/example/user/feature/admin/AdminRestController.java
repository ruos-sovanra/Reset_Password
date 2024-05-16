package com.example.user.feature.admin;

import com.example.user.feature.user.UserService;
import com.example.user.feature.user.dto.CreateUserRequest;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.feature.user.dto.ProfileUpdateRequest;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public BaseResponse<List<UserResponse>> getAllUsers(){
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsers());
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
