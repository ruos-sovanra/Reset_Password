package com.example.user.feature.user;

import com.example.user.feature.user.dto.CreateUserRequest;
import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.feature.user.dto.UserUpdateRequest;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public BaseResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest userRequest){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.updateUser(id, userRequest));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public BaseResponse<List<UserResponse>> getAllUsers(){
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById(@PathVariable String id){
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Create user")
    public BaseResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.register(userRequest));
    }


    @GetMapping("/verify")
    @Operation(summary = "Get all users by is verify")
    public BaseResponse<List<UserResponse>> getAllUsersByIsVerify(){
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsersByIsVerify());
    }

    @PutMapping("/{id}/verify")
    @Operation(summary = "Verify user")
    public BaseResponse<UserResponse> isVerified(@PathVariable String id){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.isVerified(id));
    }

}
