package com.example.user.feature.share_comment;

import com.example.user.domain.Share;
import com.example.user.domain.ShareComment;
import com.example.user.domain.User;
import com.example.user.feature.share.ShareRepository;
import com.example.user.feature.share_comment.dto.ShareCommentRequest;
import com.example.user.feature.share_comment.dto.ShareCommentResponse;
import com.example.user.feature.share_comment.dto.ShareRepliedRequest;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.ShareCommentMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShareCommentServiceImpl implements ShareCommentService{

    private final ShareCommentRepository shareCommentRepository;
    private final ShareRepository shareRepository;
    private final UserRepository userRepository;
    private final ShareCommentMapper shareCommentMapper;

    @Override
    public ShareCommentResponse createShareComment(ShareCommentRequest shareCommentRequest) {
        ShareComment shareComment = shareCommentMapper.fromRequestToResponse(shareCommentRequest);
        User user = userRepository.findById(shareCommentRequest.userId()).orElseThrow(()-> new NoSuchElementException("User not found"));
        Share share = shareRepository.findById(shareCommentRequest.shareId()).orElseThrow(()-> new NoSuchElementException("Share not found"));
        shareComment.setUser(user);
        shareComment.setShare(share);
        shareCommentRepository.save(shareComment);
        return shareCommentMapper.toShareCommentResponse(shareComment);
    }

    @Override
    public ShareCommentResponse createRepliedShareComment(ShareRepliedRequest shareRepliedRequest) {
        ShareComment parentShareComment = shareCommentRepository.findById(shareRepliedRequest.parentCommentId()).orElseThrow(()-> new NoSuchElementException("Parent share comment not found"));
        ShareComment shareComment = shareCommentMapper.responseToComment(shareRepliedRequest, parentShareComment);
        User user = userRepository.findById(shareRepliedRequest.userId()).orElseThrow(()-> new NoSuchElementException("User not found"));
        Share share = shareRepository.findById(shareRepliedRequest.shareId()).orElseThrow(()-> new NoSuchElementException("Share not found"));
        shareComment.setUser(user);
        shareComment.setShare(share);
        shareCommentRepository.save(shareComment);
        return shareCommentMapper.toShareCommentResponse(shareComment);
    }

    @Override
    public ShareCommentResponse getShareComment(String shareCommentId) {
        ShareComment shareComment = shareCommentRepository.findById(shareCommentId).orElseThrow(()-> new NoSuchElementException("Share comment not found"));
        return shareCommentMapper.toShareCommentResponse(shareComment);
    }

    @Override
    public ShareCommentResponse updateShareComment(String shareCommentId, ShareCommentRequest shareCommentRequest) {
        ShareComment shareComment = shareCommentRepository.findById(shareCommentId).orElseThrow(()-> new NoSuchElementException("Share comment not found"));
        User user = userRepository.findById(shareCommentRequest.userId()).orElseThrow(()-> new NoSuchElementException("User not found"));
        Share share = shareRepository.findById(shareCommentRequest.shareId()).orElseThrow(()-> new NoSuchElementException("Share not found"));
        shareComment.setComment(shareCommentRequest.comment());
        shareComment.setUser(user);
        shareComment.setShare(share);
        shareCommentRepository.save(shareComment);
        return shareCommentMapper.toShareCommentResponse(shareComment);
    }

    @Override
    public void deleteShareComment(String shareCommentId) {
        shareCommentRepository.deleteById(shareCommentId);
    }

    @Override
    public CustomPage<ShareCommentResponse> getAllShareComments(int page, int size, String baseUrl) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ShareComment>  shareComments = shareCommentRepository.findAll(pageable);
        return CustomPagination(shareComments.map(shareCommentMapper::toShareCommentResponse), baseUrl);
    }

    public CustomPage<ShareCommentResponse> CustomPagination(Page<ShareCommentResponse> page, String baseUrl){
        CustomPage<ShareCommentResponse> customPage = new CustomPage<>();
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
