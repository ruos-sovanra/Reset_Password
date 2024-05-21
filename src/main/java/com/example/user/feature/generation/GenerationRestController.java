package com.example.user.feature.generation;

import com.example.user.feature.generation.dto.GenerationRequest;
import com.example.user.feature.generation.dto.GenerationResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/generation")
public class GenerationRestController {

    private final GenerationService generationService;

    @GetMapping
    @Operation(summary = "Get all generations")
    public BaseResponse<List<GenerationResponse>> gerAllGenerations() {

        return BaseResponse.<List<GenerationResponse>>ok()
                .setPayload(generationService.getAllGenerations());
    }

    @PostMapping
    @Operation(summary = "Create generation")
    public BaseResponse<GenerationResponse> createGeneration(@RequestBody GenerationRequest request) {
        return BaseResponse.<GenerationResponse>createSuccess()
                .setPayload(generationService.createGeneration(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update generation")
    public BaseResponse<GenerationResponse> updateGeneration(@PathVariable String id, @RequestBody GenerationRequest request) {
        return BaseResponse.<GenerationResponse>updateSuccess()
                .setPayload(generationService.updateGeneration(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete generation")
    public BaseResponse<Void> deleteGeneration(@PathVariable String id) {
        generationService.deleteGeneration(id);
        return BaseResponse.<Void>deleteSuccess();
    }

}
