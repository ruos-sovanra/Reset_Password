package com.example.user.features.usersocialmedia;

import com.example.user.features.usersocialmedia.dto.SocialMediaRequest;
import com.example.user.features.usersocialmedia.dto.SocialMediaRespoen;
import com.example.user.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/social-media")
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @GetMapping
    public BaseResponse<List<SocialMediaRespoen>> getAllUserSocialMedia() {

        return BaseResponse.<List<SocialMediaRespoen>>ok()

                .setPayload(socialMediaService.getAllUserSocialMedia());
    }

    @PostMapping
    public BaseResponse<SocialMediaRespoen> createSocialMedia(@RequestBody SocialMediaRequest socialMediaRequest) {

        return BaseResponse.<SocialMediaRespoen>createSuccess()

                .setPayload(socialMediaService.createdSocialMedia(socialMediaRequest));
    }

    @PutMapping("/{id}")
    public BaseResponse<SocialMediaRespoen> updateSocialMedia(@RequestBody SocialMediaRequest socialMediaRequest, @PathVariable String id) {

        return BaseResponse.<SocialMediaRespoen>updateSuccess()

                .setPayload(socialMediaService.updateSocialMedia(socialMediaRequest, id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<SocialMediaRespoen> deleteSocialMedia(@PathVariable String id) {

        return BaseResponse.<SocialMediaRespoen>deleteSuccess()

                .setPayload(socialMediaService.deleteSocialMedia(id));
    }

    @GetMapping("/{id}")
    public BaseResponse<SocialMediaRespoen> getSocialById(@PathVariable String id) {

        return BaseResponse.<SocialMediaRespoen>ok()

                .setPayload(socialMediaService.getSocialById(id));
    }

    @GetMapping("/list")
    public BaseResponse<org.springframework.data.domain.Page<SocialMediaRespoen>> listSocialMedia(

            @RequestParam(required = false,defaultValue = "0")  int page, @RequestParam(required = false,defaultValue = "10") int size) {

        return BaseResponse.<Page<SocialMediaRespoen>>ok()

                .setPayload(socialMediaService.listSocialMedia(page, size));
    }


}
