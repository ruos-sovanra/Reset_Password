package com.example.user.feature.share;

import com.example.user.feature.share.dto.ShareResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shares")
@RequiredArgsConstructor
public class ShareRestController {

    private final ShareService shareService;

    @GetMapping
    @Operation(summary = "Get all shares")
    public BaseResponse<List<ShareResponse>> getAllShares() {
        return BaseResponse.<List<ShareResponse>>ok()
                .setPayload(shareService.getAllShares());
    }

    
}
