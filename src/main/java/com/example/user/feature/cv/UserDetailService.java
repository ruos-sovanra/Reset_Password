package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.AdminUpdateRequest;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.utils.CustomPage;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {

    CustomPage<UserDetailResponse> getAllUserDetails(int page, int size, String baseUrl, Optional<String> genType, Optional<String> numGen, Optional<Boolean> isGraduated, Optional<Boolean> isEmployed,Optional<String> studyAbroad,Optional<String> employType);
    UserDetailResponse getUserDetailById(String id);
    UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest);
    UserDetailResponse updateUserDetail(String id, UserDetailRequest userDetailRequest);
    UserDetailResponse adminUpdateUserDetail(String id, AdminUpdateRequest adminUpdateRequest);
}
