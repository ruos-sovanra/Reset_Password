package com.example.user.mapper;

import com.example.user.domain.AccType;
import com.example.user.domain.User;
import com.example.user.feature.user.dto.CreateUserRequest;
import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "AccTypeName", source = "accType.name")
    @Mapping(target = "roleName", source = "role.name")
//    @Mapping(target = "isDisabled", source = "isDeleted")
//    @Mapping(target = "isVerified", source = "_verified")
    UserResponse toUserResponse(User user);
    User requestToUserResponse(UserRequest userRequest);
    User createToUserResponse(CreateUserRequest userRequest);

}