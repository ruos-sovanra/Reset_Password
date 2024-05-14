package com.example.user.mapper;

import com.example.user.domain.User;
import com.example.user.feature.auth.dto.AuthRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toUser(AuthRequest loginRequest);
}
