package com.example.user.feature.abroad_study;

import com.example.user.feature.abroad_study.dto.StudyAbroadRequest;
import com.example.user.feature.abroad_study.dto.StudyAbroadResponse;
import com.example.user.utils.CustomPage;

public interface StudyAbroadService {

    StudyAbroadResponse createStudyAbroad(StudyAbroadRequest studyAbroadRequest);
    StudyAbroadResponse getStudyAbroad(String id);
    StudyAbroadResponse updateStudyAbroad(String id, StudyAbroadRequest studyAbroadRequest);
    void deleteStudyAbroad(String id);
    CustomPage<StudyAbroadResponse> getAllStudyAbroad(int page, int size,String baseUrl);
}
