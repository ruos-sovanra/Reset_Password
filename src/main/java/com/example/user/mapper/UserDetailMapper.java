package com.example.user.mapper;

import com.example.user.domain.UserDetail;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {

    UserDetailResponse toUserDetailResponse(UserDetail userDetail);
    UserDetail toUserDetail(UserDetailRequest userDetailRequest);
}
