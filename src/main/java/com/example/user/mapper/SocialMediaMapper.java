package com.example.user.mapper;

import com.example.user.domain.SocialMedia;
import com.example.user.features.usersocialmedia.dto.SocialMediaRequest;
import com.example.user.features.usersocialmedia.dto.SocialMediaRespoen;
import com.example.user.features.usersocialmedia.dto.SocialMediaUpdateRequest;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses = {
     UserMapper.class,
})
public interface SocialMediaMapper {

    @Mapping(target = "userResponse", source = "user", qualifiedByName = "toUserResponse")
    SocialMediaRespoen mapToSocialResponse(SocialMedia socialMedia);

    SocialMedia mapToSocailResposne (SocialMediaRequest socialMediaRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSocialMedia(@MappingTarget SocialMedia socialMedia, SocialMediaUpdateRequest socialMediaUpdateRequest);
}
