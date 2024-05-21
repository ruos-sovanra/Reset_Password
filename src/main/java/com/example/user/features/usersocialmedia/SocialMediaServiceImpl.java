package com.example.user.features.usersocialmedia;

import com.example.user.domain.Social;
import com.example.user.domain.SocialMedia;
import com.example.user.features.social.SocialService;
import com.example.user.features.user.UserRepository;
import com.example.user.features.usersocialmedia.dto.SocialMediaRequest;
import com.example.user.features.usersocialmedia.dto.SocialMediaRespoen;
import com.example.user.features.usersocialmedia.dto.SocialMediaUpdateRequest;
import com.example.user.mapper.SocialMapper;
import com.example.user.mapper.SocialMediaMapper;
import com.example.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialMediaServiceImpl implements SocialMediaService{

    private final UserSocialMediaRepository userSocialMediaRepository;

    private final UserMapper userMapper;

    private final SocialMediaMapper socialMediaMapper;

    private final UserRepository userRepository;
    @Override
    public List<SocialMediaRespoen> getAllUserSocialMedia() {

        var allSocialMedia = userSocialMediaRepository.findAll();

        return allSocialMedia.stream().map(socialMediaMapper::mapToSocialResponse).toList();
    }

    @Override
    public SocialMediaRespoen createdSocialMedia(SocialMediaRequest socialMediaRequest) {

        var userId = userRepository.findById(socialMediaRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var socialMedia = socialMediaMapper.mapToSocailResposne(socialMediaRequest);

        socialMedia.setUser(userId);

        var socialMedias = userSocialMediaRepository.save(socialMedia);

        return socialMediaMapper.mapToSocialResponse(socialMedias);
    }

    @Override
    public SocialMediaRespoen updateSocialMedia(SocialMediaUpdateRequest mediaUpdateRequest, String id) {

        var socialMedia = userSocialMediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social Media not found"));

        socialMediaMapper.updateSocialMedia(socialMedia, mediaUpdateRequest);

        var socialMedias = userSocialMediaRepository.save(socialMedia);

        return socialMediaMapper.mapToSocialResponse(socialMedias);
    }

    @Override
    public SocialMediaRespoen deleteSocialMedia(String id) {

        var socialMedia = userSocialMediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social Media not found"));

        userSocialMediaRepository.delete(socialMedia);

        return socialMediaMapper.mapToSocialResponse(socialMedia);
    }

    @Override
    public SocialMediaRespoen getSocialById(String id) {

        var socialMedia = userSocialMediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social Media not found"));

        return socialMediaMapper.mapToSocialResponse(socialMedia);

    }

    @Override
    public Page<SocialMediaRespoen> listSocialMedia(int page, int size) {


        if(page < 0) {
            throw new IllegalArgumentException("Page number cannot be less than 0");
        }
        if(size < 1) {
            throw new IllegalArgumentException("Size number cannot be less than 1");
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<SocialMedia> socialMediaPage = userSocialMediaRepository.findAll(pageRequest);


        return socialMediaPage.map(socialMediaMapper::mapToSocialResponse);
    }

}
