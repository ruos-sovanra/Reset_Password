package com.example.user.feature.user;

import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.feature.user.dto.UserUpdateRequest;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
