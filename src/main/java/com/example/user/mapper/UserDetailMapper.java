package com.example.user.mapper;

import com.example.user.domain.UserDetail;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {

    @Mapping(target = "employTypeName", source = "employType.employType")
    @Mapping(target = "studyAbroadName", source = "studyAbroad.country")
    @Mapping(target = "userName", source = "user.username")
    UserDetailResponse toUserDetailResponse(UserDetail userDetail);
    UserDetail toUserDetail(UserDetailRequest userDetailRequest);


}
