package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Comment;
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
@DisplayName("댓글 조회 리퍼지터리 구현")
public class CommentQueryRepositoryImplTest {

    @Autowired
    private CommentQueryRepository commentQueryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostQueryRepository postQueryRepository;

    @Test
    @DisplayName("게시글id로 해당 게시글의 모든 댓글 조회")
    void findAllByPostId(){
        //given
        Post post=Post.builder()
                .memberId(1L)
                .title("findAllByPostId")
                .content("게시글id로 댓글 조회")
                .comments(List.of())
                .time(LocalDateTime.now())
                .build();

        Post savedPost=postRepository.save(post);

        Comment comment1=Comment.builder()
                .postId(savedPost.getId())
                .memberId(1L)
                .content("댓글1입니다")
                .time(LocalDateTime.now())
                .build();

        Comment savedComment1=commentRepository.save(comment1);

        Comment comment2=Comment.builder()
                .postId(savedPost.getId())
                .memberId(1L)
                .content("댓글2입니다")
                .time(LocalDateTime.now())
                .build();

        Comment savedComment2=commentRepository.save(comment2);

        //when
        List<Comment> result=commentQueryRepository.findAllByPostId(savedPost.getId());
        List<Comment> expected=List.of(savedComment1,savedComment2);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);

    }


}
