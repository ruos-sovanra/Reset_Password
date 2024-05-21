package com.example.user.feature.share;

import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;

import java.util.List;

public interface ShareService {
    List<ShareResponse> getAllShares();
    ShareResponse getShareById(String id);
    ShareResponse createShare(ShareRequest share);
    ShareResponse updateShare(String id, ShareRequest share);
    void deleteShare(String id);
}