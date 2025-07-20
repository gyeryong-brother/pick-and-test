package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.exception;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum NoticeboardDataExceptionType implements BaseExceptionType {
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 댓글입니다"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 게시글입니다")
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
