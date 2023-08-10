package com.wanted.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404,"사용자를 찾을 수 없습니다."),
    NOTE_NOT_FOUND(404,"게시글을 찾을 수 없습니다."),
    NOTE_CANNOT_DELETE(403, "게시글을 삭제할 수 없습니다."),
    NOTE_CANNOT_UPDATE(403, "게시글을 수정할 수 없습니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
