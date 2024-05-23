package com.example.user.mapper;

import com.example.user.domain.Social;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SocialMapper {
    @Named("toPostResponse")
    @Mapping(target = "postTypeName", source = "postType.type")
    PostResponse toPostResponse(Social social);
    Social toSocial(PostRequest postRequest);
}
