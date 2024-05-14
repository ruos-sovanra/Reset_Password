package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
    User toUserResponse(UserRequest userRequest);
}
