package com.example.user.feature.generation.dto;

import lombok.Builder;

@Builder
public record GenerationResponse(String id,
                                 String genType,
                                 Integer numGen){
}
