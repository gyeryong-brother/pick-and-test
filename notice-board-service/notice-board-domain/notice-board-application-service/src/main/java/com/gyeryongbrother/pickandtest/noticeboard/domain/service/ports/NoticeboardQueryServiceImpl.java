package com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports;

import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.PostsResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto.SimplePostResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardQueryService;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeboardQueryServiceImpl implements NoticeboardQueryService {

    private final PostQueryRepository postQueryRepository;

    @Override
    public PostsResponse findAllPosts() {
        List<Post> posts = postQueryRepository.findAll();
        List<SimplePostResponse> simplePosts = posts.stream()
                .map(SimplePostResponse::from)
                .toList();
        return new PostsResponse(simplePosts);
    }

    @Override
    public PostResponse findPostById(Long postId) {
        Post post = postQueryRepository.findById(postId);
        return PostResponse.from(post);
    }
}
