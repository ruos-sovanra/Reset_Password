package com.example.user.feature.user;

import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserById(String id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(String id, UserRequest userRequest);
    void deleteUser(String id);

}
