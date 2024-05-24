package com.example.user.features.user;

import com.example.user.features.user.dto.*;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserById(String id);
    UserResponse register(UserRequest userRequest);
    UserResponse updateUser(String id, UserUpdateRequest userRequest);
    UserResponse deleteUser(String id);
    UserResponse updateProfile(String id, ProfileUpdateRequest profileUpdateRequest);
    UserResponse isVerified(String id);
    UserResponse isDisabled(String id);
    UserResponse createUsers(CreateUserRequest userRequest);
    List<UserResponse> getAllUsersByIsVerify();
}