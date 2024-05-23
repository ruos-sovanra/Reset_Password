package com.example.user.feature.user;

import com.example.user.feature.user.dto.*;
import com.example.user.utils.CustomPage;

import java.util.List;
import java.util.Optional;

public interface UserService {

    CustomPage<UserResponse> getAllUsers(int page, int size, String baseUrl);
    UserResponse getUserById(String id);
    UserResponse register(UserRequest userRequest);
    UserResponse updateUser(String id, UserUpdateRequest userRequest);
    void deleteUser(String id);
    UserResponse updateProfile(String id, ProfileUpdateRequest profileUpdateRequest);
    UserResponse isVerified(String id);
    UserResponse isDisabled(String id);
    UserResponse createUsers(CreateUserRequest userRequest);
    List<UserResponse> getAllUsersByIsVerify();
}
