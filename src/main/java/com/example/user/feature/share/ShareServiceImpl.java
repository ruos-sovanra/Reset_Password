package com.example.user.feature.share;

import com.example.user.domain.Share;
import com.example.user.domain.Social;
import com.example.user.domain.User;
import com.example.user.feature.share.dto.LikeShareUpdateRequest;
import com.example.user.feature.share.dto.ShareRequest;
import com.example.user.feature.share.dto.ShareResponse;
import com.example.user.feature.share.dto.ShareUpdateRequest;
import com.example.user.feature.social.SocialRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.mapper.ShareMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public CustomPage<ShareResponse> getAllShares(int page, int size, String baseUrl) {
        Page<Share> shares = shareRepository.findAll(Pageable.ofSize(size).withPage(page));
        return CustomPagination(shares.map(shareMapper::entityToResponse), baseUrl);
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
        shareEntity.setLikes(0);
        shareEntity.setShares(0);
        shareRepository.save(shareEntity);

        return shareMapper.entityToResponse(shareEntity);
    }

    @Override
    public ShareResponse updateShare(String id, ShareUpdateRequest share) {
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

    @Override
    public ShareResponse updateLikes(String id, LikeShareUpdateRequest likeShareUpdateRequest) {
        Share share = shareRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Share not found")
        );
        share.setLikes(share.getLikes() + likeShareUpdateRequest.likes());
        shareRepository.save(share);
        return shareMapper.entityToResponse(share);
    }

    public CustomPage<ShareResponse> CustomPagination(Page<ShareResponse> page, String baseUrl){
        CustomPage<ShareResponse> customPage = new CustomPage<>();
        if(page.hasNext()){
            customPage.setNext(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize());
        }
        if (page.hasPrevious()){
            customPage.setPrevious(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize());
        }
        customPage.setTotal((int) page.getTotalElements());
        customPage.setResults(page.getContent());
        return customPage;
    }
}
