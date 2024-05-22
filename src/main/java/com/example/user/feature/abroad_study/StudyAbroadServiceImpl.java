package com.example.user.feature.abroad_study;

import com.example.user.domain.StudyAbroad;
import com.example.user.feature.abroad_study.dto.StudyAbroadRequest;
import com.example.user.feature.abroad_study.dto.StudyAbroadResponse;
import com.example.user.feature.employ.dto.EmployTypeResponse;
import com.example.user.mapper.StudyAbroadMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyAbroadServiceImpl implements StudyAbroadService{

    private final StudyAbroadRepository studyAbroadRepository;
    private final StudyAbroadMapper studyAbroadMapper;

    @Override
    public StudyAbroadResponse createStudyAbroad(StudyAbroadRequest studyAbroadRequest) {
        StudyAbroad studyAbroad = studyAbroadMapper.requestToStudyAbroad(studyAbroadRequest);
        studyAbroadRepository.save(studyAbroad);
        return studyAbroadMapper.toStudyAbroadResponse(studyAbroad);
    }

    @Override
    public StudyAbroadResponse getStudyAbroad(String id) {
        StudyAbroad studyAbroad = studyAbroadRepository.findById(id).orElseThrow();
        return studyAbroadMapper.toStudyAbroadResponse(studyAbroad);
    }

    @Override
    public StudyAbroadResponse updateStudyAbroad(String id, StudyAbroadRequest studyAbroadRequest) {
        StudyAbroad studyAbroad = studyAbroadRepository.findById(id).orElseThrow();
        studyAbroad.setCountry(studyAbroadRequest.country());
        studyAbroadRepository.save(studyAbroad);
        return studyAbroadMapper.toStudyAbroadResponse(studyAbroad);
    }

    @Override
    public void deleteStudyAbroad(String id) {

        studyAbroadRepository.deleteById(id);

    }

    @Override
    public CustomPage<StudyAbroadResponse> getAllStudyAbroad(int page, int size, String baseUrl) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudyAbroad> studyAbroad = studyAbroadRepository.findAll(pageable);

        return CustomPagination(studyAbroad.map(studyAbroadMapper::toStudyAbroadResponse), baseUrl);

    }

    public CustomPage<StudyAbroadResponse> CustomPagination(Page<StudyAbroadResponse> page, String baseUrl){
        CustomPage<StudyAbroadResponse> customPage = new CustomPage<>();
        if(page.hasNext()){
            customPage.setNext(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize());
        }
        if (page.hasPrevious()){
            customPage.setPrevious(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize());
        }
        customPage.setTotal((int) page.getTotalElements());
        customPage.setResults(page.getContent());
        return customPage;
    }

}
