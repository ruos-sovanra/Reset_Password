package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.AdminUpdateRequest;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.utils.CustomPage;

import java.util.List;

public interface UserDetailService {

    CustomPage<UserDetailResponse> getAllUserDetails(int page, int size, String baseUrl);
    UserDetailResponse getUserDetailById(String id);
    UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest);
    UserDetailResponse updateUserDetail(String id, UserDetailRequest userDetailRequest);
    UserDetailResponse adminUpdateUserDetail(String id, AdminUpdateRequest adminUpdateRequest);
}
