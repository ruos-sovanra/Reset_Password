package com.example.user.feature.user;

import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;
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

    @GetMapping
    @Operation(summary = "Get all users")
    public BaseResponse<List<UserResponse>> getAllUsers(){
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById(String id){
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Create user")
    public BaseResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public BaseResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest){
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public BaseResponse<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return BaseResponse.<Void>ok();
    }

}
