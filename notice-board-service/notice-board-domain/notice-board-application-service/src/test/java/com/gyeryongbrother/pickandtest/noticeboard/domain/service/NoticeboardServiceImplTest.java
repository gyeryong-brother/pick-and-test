package com.gyeryongbrother.pickandtest.noticeboard.domain.service;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.input.NoticeboardService;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.CommentRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostQueryRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("게시판 서비스를 구현한다")
public class NoticeboardServiceImplTest {

    @Mock
    private PostQueryRepository postQueryRepository;

    @Mock
    private CommentQueryRepository commentQueryRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    private NoticeboardService noticeboardService;

    @Test
    @DisplayName("게시글을 작성한다")
    void writeComment() {
        //given

        //when

        //then
    }
}
