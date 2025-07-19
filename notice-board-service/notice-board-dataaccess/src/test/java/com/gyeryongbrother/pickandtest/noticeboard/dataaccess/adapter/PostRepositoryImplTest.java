package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;


import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("댓글 리퍼지터리 구현")
public class PostRepositoryImplTest {

    @Autowired
    private CommentQueryRepository commentQueryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostQueryRepository postQueryRepository;

    @Test
    @DisplayName("모든 게시글 조회")
    void findAll(){
        //given
        Post post1=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost1=postRepository.save(post1);

        Post post2=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost2=postRepository.save(post1);

        Post post3=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost3=postRepository.save(post3);

        //when
        List<Post> result=postQueryRepository.findAll();
        List<Post> expected=List.of(savedPost1,savedPost2,savedPost3);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("게시글 id로 게시글 삭제")
    void deleteById(){
        //given
        Post post1=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost1=postRepository.save(post1);

        Post post2=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost2=postRepository.save(post1);

        Post post3=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost3=postRepository.save(post3);

        //when
        postRepository.deleteById(savedPost2.getId());
        List<Post> result=postQueryRepository.findAll();
        List<Post> expected=List.of(savedPost1,savedPost3);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
