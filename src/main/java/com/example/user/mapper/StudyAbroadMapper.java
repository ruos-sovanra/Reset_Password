package com.example.user.mapper;

import com.example.user.domain.StudyAbroad;
import com.example.user.feature.abroad_study.dto.StudyAbroadRequest;
import com.example.user.feature.abroad_study.dto.StudyAbroadResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyAbroadMapper {

    StudyAbroadResponse toStudyAbroadResponse(StudyAbroad studyAbroad);
    StudyAbroad requestToStudyAbroad(StudyAbroadRequest studyAbroadRequest);
}
