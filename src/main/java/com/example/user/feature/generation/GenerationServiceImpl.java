package com.example.user.feature.generation;

import com.example.user.domain.Generation;
import com.example.user.feature.generation.dto.GenerationRequest;
import com.example.user.feature.generation.dto.GenerationResponse;
import com.example.user.mapper.GenerationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService {

    private final GenerationRepository generationRepository;
    private final GenerationMapper generationMapper;

    @Override
    public GenerationResponse createGeneration(GenerationRequest request) {

        Generation generation = generationMapper.toGeneration(request);

        return generationMapper.toGenerationResponse(generationRepository.save(generation));
    }

    @Override
    public GenerationResponse updateGeneration(String id, GenerationRequest request) {

        Generation generation = generationRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Generation not found")
        );

        generation.setNameType(request.nameType());

        generation.setNumGen(request.numGen());

        generationRepository.save(generation);

        return generationMapper.toGenerationResponse(generation);
    }

    @Override
    public void deleteGeneration(String id) {
        generationRepository.deleteById(id);
    }

    @Override
    public List<GenerationResponse> getAllGenerations() {
        List<Generation> generations = generationRepository.findAll();
        return generations.stream()
                .map(generationMapper::toGenerationResponse)
                .toList();
    }
}
