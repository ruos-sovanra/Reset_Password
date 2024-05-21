package com.example.user.feature.share;

import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;
import com.example.user.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @Operation(summary = "Get share by id")
    public BaseResponse<ShareResponse> getShareById(String id) {
        return BaseResponse.<ShareResponse>ok()
                .setPayload(shareService.getShareById(id));
    }

    @PostMapping
    @Operation(summary = "Create share")
    public BaseResponse<ShareResponse> createShare(@RequestBody ShareRequest share) {
        return BaseResponse.<ShareResponse>createSuccess()
                .setPayload(shareService.createShare(share));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update share")
    public BaseResponse<ShareResponse> updateShare(@PathVariable String id, @RequestBody ShareRequest share) {
        return BaseResponse.<ShareResponse>updateSuccess()
                .setPayload(shareService.updateShare(id, share));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete share")
    public BaseResponse<Void> deleteShare(@PathVariable String id) {
        shareService.deleteShare(id);
        return BaseResponse.<Void>deleteSuccess();
    }

    
}
