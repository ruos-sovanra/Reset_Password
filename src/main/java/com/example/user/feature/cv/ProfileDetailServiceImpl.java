package com.example.user.feature.cv;

import com.example.user.domain.Generation;
import com.example.user.domain.User;
import com.example.user.domain.UserDetail;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.feature.generation.GenerationRepository;
import com.example.user.features.user.UserRepository;
import com.example.user.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileDetailServiceImpl implements UserDetailService{

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;
    private final GenerationRepository generationRepository;
    private final UserDetailMapper userDetailMapper;

    @Override
    public List<UserDetailResponse> getAllUserDetails() {

        List<UserDetail> userDetailList = userDetailRepository.findAll();
        return userDetailList.stream()
                .map(userDetailMapper::toUserDetailResponse)
                .toList();
    }

    @Override
    public UserDetailResponse getUserDetailById(String id) {
        return null;
    }

    @Override
    public UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest) {
        UserDetail userDetail = userDetailMapper.toUserDetail(userDetailRequest);
        User user = userRepository.findById(userDetailRequest.userId()).orElseThrow(
                () -> new NoSuchElementException("User not found")

        );
        Generation generation = generationRepository.findById(userDetailRequest.generationId()).orElseThrow(
                () -> new NoSuchElementException("Generation not found")
        );
        userDetail.setGeneration(generation);
        userDetail.setUser(user);
        userDetailRepository.save(userDetail);
        return userDetailMapper.toUserDetailResponse(userDetail);
    }

    @Override
    public UserDetailResponse updateUserDetail(String id, UserDetailRequest userDetailRequest) {
        UserDetail userDetail = userDetailRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("UserDetail not found")
        );
        User user = userRepository.findById(userDetailRequest.userId()).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );
        Generation generation = generationRepository.findById(userDetailRequest.generationId()).orElseThrow(
                () -> new NoSuchElementException("Generation not found")
        );

        userDetail.setUser(user);
        userDetail.setGeneration(generation);
        userDetail.setAddress(userDetailRequest.address());
        userDetail.setAchievements(userDetailRequest.achievements());
        userDetail.setEducations(userDetailRequest.educations());
        userDetail.setWorkExperiences(userDetailRequest.workExperiences());
        userDetail.setSkills(userDetailRequest.skills());
        userDetail.setInterests(userDetailRequest.interests());
        userDetail.setLanguages(userDetailRequest.languages());
        userDetail.setFirstName(userDetailRequest.firstName());
        userDetail.setLastName(userDetailRequest.lastName());
        userDetail.setTelephone(userDetailRequest.telephone());
        userDetail.setEmail(userDetailRequest.email());
        userDetail.setGender(userDetailRequest.gender());
        userDetail.setNationality(userDetailRequest.nationality());
        userDetailRepository.save(userDetail);
        return userDetailMapper.toUserDetailResponse(userDetail);
    }
}
