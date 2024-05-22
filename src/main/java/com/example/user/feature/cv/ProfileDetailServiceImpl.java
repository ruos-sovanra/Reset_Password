package com.example.user.feature.cv;

import com.example.user.domain.*;
import com.example.user.feature.abroad_study.StudyAbroadRepository;
import com.example.user.feature.cv.dto.AdminUpdateRequest;
import com.example.user.feature.cv.dto.UserDetailRequest;
import com.example.user.feature.cv.dto.UserDetailResponse;
import com.example.user.feature.employ.EmployTypeRepository;
import com.example.user.feature.generation.GenerationRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.UserDetailMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileDetailServiceImpl implements UserDetailService{

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;
    private final GenerationRepository generationRepository;
    private final StudyAbroadRepository studyAbroadRepository;
    private final EmployTypeRepository employTypeRepository;
    private final UserDetailMapper userDetailMapper;


    @Override
    public CustomPage<UserDetailResponse> getAllUserDetails(int page, int size, String baseUrl) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<UserDetail> userDetailPage = userDetailRepository.findAll(pageable);
        return CustomPagination(userDetailPage.map(userDetailMapper::toUserDetailResponse), baseUrl);
    }

    public CustomPage<UserDetailResponse> CustomPagination(Page<UserDetailResponse> page, String baseUrl) {
        CustomPage<UserDetailResponse> customPage = new CustomPage<>();

        if (page.hasPrevious()) {
            customPage.setPrevious(baseUrl + "?page=" + (page.getNumber() -1) + "&size=" + page.getSize());
        }

        if (page.hasNext()) {
            customPage.setNext(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize());
        }

        customPage.setTotal(page.getTotalPages());
        customPage.setResults(page.getContent());

        return customPage;
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
        userDetail.setUser(user);
        userDetail.setIsEmployed(false);
        userDetail.setIsGraduated(false);
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

        userDetail.setUser(user);
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

    @Override
    public UserDetailResponse adminUpdateUserDetail(String id, AdminUpdateRequest adminUpdateRequest) {

        UserDetail userDetail = userDetailRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("UserDetail not found")
        );

        Generation generation = generationRepository.findById(adminUpdateRequest.generationId()).orElseThrow(
                () -> new NoSuchElementException("Generation not found")
        );

        EmployType employType = employTypeRepository.findByEmployType(adminUpdateRequest.employTypeName());
        StudyAbroad studyAbroad = studyAbroadRepository.findByCountry(adminUpdateRequest.countryName());

        userDetail.setEmployType(employType);
        userDetail.setGeneration(generation);
        userDetail.setStudyAbroad(studyAbroad);

        return userDetailMapper.toUserDetailResponse(userDetail);
    }
}
