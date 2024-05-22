package com.example.user.feature.abroad_study;

import com.example.user.feature.abroad_study.dto.StudyAbroadRequest;
import com.example.user.feature.abroad_study.dto.StudyAbroadResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/study-abroad")
@RequiredArgsConstructor
public class StudyAbroadRestController {

    private final StudyAbroadService studyAbroadService;

    @GetMapping
    @Operation(summary = "Get all study abroad")
    public ResponseEntity<CustomPage<StudyAbroadResponse>> getAllStudyAbroad(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/study-abroad";
        CustomPage<StudyAbroadResponse> studyAbroadResponseCustomPage = studyAbroadService.getAllStudyAbroad(page, size, baseUrl);

        return ResponseEntity.ok(studyAbroadResponseCustomPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get study abroad by id")
    public BaseResponse<StudyAbroadResponse> getStudyAbroad(@PathVariable String id) {
        return BaseResponse.<StudyAbroadResponse>ok()
                .setPayload(studyAbroadService.getStudyAbroad(id));
    }

    @PostMapping
    @Operation(summary = "Create study abroad")
    public BaseResponse<StudyAbroadResponse> createStudyAbroad(@RequestBody StudyAbroadRequest studyAbroadRequest) {
        return BaseResponse.<StudyAbroadResponse>createSuccess()
                .setPayload(studyAbroadService.createStudyAbroad(studyAbroadRequest));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update study abroad")
    public BaseResponse<StudyAbroadResponse> updateStudyAbroad(@PathVariable String id, @RequestBody StudyAbroadRequest studyAbroadRequest) {
        return BaseResponse.<StudyAbroadResponse>updateSuccess()
                .setPayload(studyAbroadService.updateStudyAbroad(id, studyAbroadRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete study abroad")
    public BaseResponse<Void> deleteStudyAbroad(@PathVariable String id) {
        studyAbroadService.deleteStudyAbroad(id);
        return BaseResponse.<Void>deleteSuccess();
    }


    }
