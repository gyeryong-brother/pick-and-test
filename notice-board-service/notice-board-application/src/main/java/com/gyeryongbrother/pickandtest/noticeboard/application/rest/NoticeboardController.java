package com.gyeryongbrother.pickandtest.noticeboard.application.rest;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeleteCommentCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.DeletePostCommand;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostsResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WriteCommentRequest;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.WritePostRequest;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardQueryService;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noticeboard")
public class NoticeboardController {

    private final NoticeboardService noticeboardService;
    private final NoticeboardQueryService noticeboardQueryService;

    @GetMapping
    ResponseEntity<PostsResponse> findAllPosts(){
        PostsResponse postResponses=noticeboardQueryService.findAllPosts();
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/{postId}")
    ResponseEntity<PostResponse> findPostById(@PathVariable Long postId){
        PostResponse postResponse=noticeboardQueryService.findPostById(postId);
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping
    ResponseEntity<PostResponse> writePost(@RequestHeader Long memberId,@RequestBody WritePostRequest writePostRequest){
        PostResponse postResponse=noticeboardService.writePost(writePostRequest.toCommand(memberId));
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping("/{postId}/comment")
    ResponseEntity<PostResponse> writeComment(
            @PathVariable Long postId,
            @RequestHeader Long memberId,
            @RequestBody WriteCommentRequest writeCommentRequest
    ){
      PostResponse postResponse=noticeboardService.writeComment(writeCommentRequest.toCommand(memberId,postId));
      return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<PostsResponse> deletePost(@RequestHeader Long memberId, @PathVariable Long postId){
        PostsResponse postsResponse=noticeboardService.deletePost(new DeletePostCommand(memberId,postId));
        return ResponseEntity.ok(postsResponse);
    }

    @DeleteMapping("/{postId}/{commentId}")
    ResponseEntity<PostResponse> deleteComment(
            @RequestHeader Long memberId,
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        DeleteCommentCommand deleteCommentCommand=new DeleteCommentCommand(commentId,postId,memberId);
        PostResponse postResponse=noticeboardService
                .deleteComment(deleteCommentCommand);
        return ResponseEntity.ok(postResponse);
    }
}
