package com.example.user.feature.share;

import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;
import com.example.user.utils.CustomPage;

import java.util.List;

public interface ShareService {
    CustomPage<ShareResponse> getAllShares(int page, int size, String baseUrl);
    ShareResponse getShareById(String id);
    ShareResponse createShare(ShareRequest share);
    ShareResponse updateShare(String id, ShareRequest share);
    void deleteShare(String id);
}
