package com.example.user.feature.generation;

import com.example.user.feature.generation.dto.GenerationRequest;
import com.example.user.feature.generation.dto.GenerationResponse;

import java.util.List;

public interface GenerationService {

    GenerationResponse createGeneration(GenerationRequest request);
    GenerationResponse updateGeneration(String id, GenerationRequest request);
    void deleteGeneration(String id);
    List<GenerationResponse> getAllGenerations();
}
