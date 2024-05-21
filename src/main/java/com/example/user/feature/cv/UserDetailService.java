package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;

import java.util.List;

public interface UserDetailService {

    List<UserDetailResponse> getAllUserDetails();
    UserDetailResponse getUserDetailById(String id);
    UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest);
    UserDetailResponse updateUserDetail(String id, UserDetailRequest userDetailRequest);
}
