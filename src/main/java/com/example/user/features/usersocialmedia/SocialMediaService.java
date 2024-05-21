package com.example.user.features.usersocialmedia;

import com.example.user.features.usersocialmedia.dto.SocialMediaRequest;
import com.example.user.features.usersocialmedia.dto.SocialMediaRespoen;
import com.example.user.features.usersocialmedia.dto.SocialMediaUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SocialMediaService {

    List<SocialMediaRespoen> getAllUserSocialMedia();

    SocialMediaRespoen createdSocialMedia(SocialMediaRequest socialMediaRequest);

    SocialMediaRespoen updateSocialMedia(SocialMediaUpdateRequest socialMediaUpdateRequest, String id);

    SocialMediaRespoen deleteSocialMedia(String id);

    SocialMediaRespoen getSocialById (String id);

    Page<SocialMediaRespoen> listSocialMedia(int page, int size);




}
