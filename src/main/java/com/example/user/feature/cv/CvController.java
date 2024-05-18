package com.example.user.feature.cv;

import com.example.user.feature.cv.dto.CvRespone;
import com.example.user.utils.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cv")
@RequiredArgsConstructor
public class CvController {

    private final CvService cvService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CvRespone> uploadSingleFile(
            @RequestPart("file") MultipartFile file, HttpServletRequest request
    ) {
        return BaseResponse
                .<CvRespone>createSuccess()
                .setPayload(cvService.uploadSingleFile(file, request));
    }

    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
//    return payload as List<FileResponse>
    public BaseResponse<List<CvRespone>> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {
//        return fileService.uploadMultipleFiles(files);
        return null;
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        return cvService.serveFile(fileName,request);
    }
    @DeleteMapping("{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        cvService.deleteFile(fileName);
        return "file is deleted successfully!";
    }
}
