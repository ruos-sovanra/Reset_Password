package com.example.user.mapper;

import com.example.user.domain.Share;
import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {SocialMapper.class})
public interface ShareMapper {


    Share requestToEntity(ShareRequest request);
    @Mapping(target = "post", source = "social", qualifiedByName = "toPostResponse")
    ShareResponse entityToResponse(Share share);
}
