package com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum NoticeboardServiceExceptionType implements BaseExceptionType{

    CAN_NOT_DELETE_POST(FORBIDDEN, "게시글을 삭제할 권한이 없습니다"),
    CAN_NOT_DELETE_COMMENT(FORBIDDEN, "댓글을 삭제할 권한이 없습니다"),
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
