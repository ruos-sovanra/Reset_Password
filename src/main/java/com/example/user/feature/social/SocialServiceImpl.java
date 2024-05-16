package com.example.user.feature.social;

import com.example.user.domain.PostType;
import com.example.user.domain.Social;
import com.example.user.domain.User;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.SocialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository socialRepository;
    private final PostTypeRepository postTypeRepository;
    private final UserRepository userRepository;
    private final SocialMapper socialMapper;

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