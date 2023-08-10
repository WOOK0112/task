package com.wanted.note.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteResponseDto {
    private long noteId;

    private String writer;

    private String title;

    private String content;
}
