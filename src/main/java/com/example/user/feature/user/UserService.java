package com.example.user.feature.user;

import com.example.user.feature.user.dto.*;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserById(String id);
    UserResponse register(UserRequest userRequest);
    UserResponse updateUser(String id, UserUpdateRequest userRequest);
    void deleteUser(String id);
    UserResponse updateProfile(String id, ProfileUpdateRequest profileUpdateRequest);
    UserResponse isVerified(String id);
    UserResponse createUsers(CreateUserRequest userRequest);
}
