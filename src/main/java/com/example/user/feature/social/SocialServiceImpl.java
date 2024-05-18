package com.example.user.feature.social;

import com.example.user.domain.*;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.repo.ThumbnailRepository;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.SocialMapper;
import com.example.user.mapper.ThumnailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository socialRepository;
    private final PostTypeRepository postTypeRepository;
    private final UserRepository userRepository;
    private final ThumbnailRepository thumbnailRepository;
    private final SocialMapper socialMapper;
    private final ThumnailMapper thumnailMapper;

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Social social = socialMapper.toSocial(postRequest);
        PostType postType = postTypeRepository.findByType("SOCIAL")
                .orElseThrow(() -> new RuntimeException("Post type not found"));
        User user = userRepository.findById(postRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        social.setUser(user);
        social.setPostType(postType);
        socialRepository.save(social);

        List<com.example.user.feature.social.dto.Thumbnail> dtoThumbnails = postRequest.thumbnails();
        if (dtoThumbnails != null && !dtoThumbnails.isEmpty()) {
            for (com.example.user.feature.social.dto.Thumbnail dtoThumbnail : dtoThumbnails) {
                Thumbnail domainThumbnail = thumnailMapper.toThumbnail(dtoThumbnail);
                domainThumbnail.setSocial(social);
                thumbnailRepository.save(domainThumbnail);
            }
        }



//        List<Thumbnail> thumbnails = postRequest.thumbnails();
//        if (thumbnails != null && !thumbnails.isEmpty()) {
//            for (Thumbnail thumbnail : thumbnails) {
//                thumbnail.setSocial(social);
//                thumbnailRepository.save(thumbnail);
//            }
//        }

        return socialMapper.toPostResponse(social);
    }

    @Override
    public List<PostResponse> getPosts() {
        List<Social> socials = socialRepository.findAll();
        return socials.stream()
                .map(socialMapper::toPostResponse)
                .toList();
    }


    @Override
    public PostResponse updatePost(String postId, PostRequest postRequest) {
        Social social = socialRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostType postType = postTypeRepository.findByType("SOCIAL")
                .orElseThrow(() -> new RuntimeException("Post type not found"));
        User user = userRepository.findById(postRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        social.setUser(user);
        social.setPostType(postType);
        social.setCaption(postRequest.caption());
        socialRepository.save(social);
        return socialMapper.toPostResponse(social);
    }

    @Override
    public void deletePost(String postId) {
        socialRepository.deleteById(postId);
    }
}
