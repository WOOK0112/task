package com.wanted.note.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NotePostDto {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
