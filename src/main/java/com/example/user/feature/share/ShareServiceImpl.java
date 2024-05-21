package com.example.user.feature.share;

import com.example.user.domain.Share;
import com.example.user.domain.Social;
import com.example.user.domain.User;
import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;
import com.example.user.feature.social.SocialRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.ShareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService{

    private final ShareRepository shareRepository;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;
    private final ShareMapper shareMapper;


    @Override
    public List<ShareResponse> getAllShares() {
        List<Share> shares = shareRepository.findAll();
        return shares.stream()
                .map(shareMapper::entityToResponse)
                .toList();
    }

    @Override
    public ShareResponse getShareById(String id) {
        Share share = shareRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Share not found")
        );
        return shareMapper.entityToResponse(share);
    }

    @Override
    public ShareResponse createShare(ShareRequest share) {
        Share shareEntity = shareMapper.requestToEntity(share);
        User user = userRepository.findById(share.userId()).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );
        Social social = socialRepository.findById(share.socialId()).orElseThrow(
                () -> new NoSuchElementException("Social not found")
        );
        shareEntity.setUser(user);
        shareEntity.setSocial(social);
        shareRepository.save(shareEntity);

        return shareMapper.entityToResponse(shareEntity);
    }

    @Override
    public ShareResponse updateShare(String id, ShareRequest share) {
        Share shareEntity = shareRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Share not found")
        );
       shareEntity.setCaption(share.caption());
       shareRepository.save(shareEntity);
        return shareMapper.entityToResponse(shareEntity);
    }

    @Override
    public void deleteShare(String id) {
        shareRepository.deleteById(id);
    }
}
