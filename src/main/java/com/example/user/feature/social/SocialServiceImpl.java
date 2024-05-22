package com.example.user.feature.social;

import com.example.user.domain.PostType;
import com.example.user.domain.Social;
import com.example.user.domain.Thumbnail;
import com.example.user.domain.User;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.repo.ThumbnailRepository;
import com.example.user.feature.social.dto.PostRequest;
import com.example.user.feature.social.dto.PostResponse;
import com.example.user.feature.user.UserRepository;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.mapper.SocialMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Social social = socialMapper.toSocial(postRequest);
        PostType postType = postTypeRepository.findByType("SOCIAL")
                .orElseThrow(() -> new RuntimeException("Post type not found"));
        User user = userRepository.findById(postRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        social.setUser(user);
        social.setLikes(0);
        social.setShares(0);
        social.setPostType(postType);
        socialRepository.save(social);

          List<Thumbnail> thumbnails = social.getThumbnails();

        System.out.println("thumbnails = " + thumbnails);
        for (Thumbnail thumbnail : thumbnails) {
            thumbnail.setSocial(social);
            thumbnailRepository.save(thumbnail);
        }


        return socialMapper.toPostResponse(social);
    }

    @Override
    public CustomPage<PostResponse> getPosts(int page, int size, String baseUrl) {
        Page<Social> socials = socialRepository.findAll(Pageable.ofSize(size).withPage(page));
        return CustomPagination(socials.map(socialMapper::toPostResponse), baseUrl);
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

    public CustomPage<PostResponse> CustomPagination(Page<PostResponse> page, String baseUrl){
        CustomPage<PostResponse> customPage = new CustomPage<>();
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
