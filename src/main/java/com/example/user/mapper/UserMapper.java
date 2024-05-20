package com.example.user.mapper;

import com.example.user.domain.SocialMedia;
import com.example.user.domain.User;
import com.example.user.features.user.dto.CreateUserRequest;
import com.example.user.features.user.dto.UserRequest;
import com.example.user.features.user.dto.UserResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toUserResponse")
    @Mapping(target = "AccTypeName", source = "accType.name")
    @Mapping(target = "roleName", source = "role.name")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    UserResponse toUserResponse(User user);
    User requestToUserResponse(UserRequest userRequest);
    User createToUserResponse(CreateUserRequest userRequest);




}