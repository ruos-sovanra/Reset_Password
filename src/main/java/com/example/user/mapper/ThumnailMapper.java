package com.example.user.mapper;

import com.example.user.domain.Thumbnail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface ThumnailMapper {
    @Mapping(target = "social", ignore = true)
    Thumbnail toThumbnail(com.example.user.feature.social.dto.Thumbnail postRequest);
}
