package com.example.user.mapper;

import com.example.user.domain.Social;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialMapper {
    PostResponse toPostResponse(Social social);
    Social toSocial(PostRequest postRequest);
}
