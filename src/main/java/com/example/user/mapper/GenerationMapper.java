package com.example.user.mapper;

import com.example.user.domain.Generation;
import com.example.user.feature.generation.dto.GenerationRequest;
import com.example.user.feature.generation.dto.GenerationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenerationMapper {

    GenerationResponse toGenerationResponse(Generation generation);
    Generation toGeneration(GenerationRequest generationRequest);
}
