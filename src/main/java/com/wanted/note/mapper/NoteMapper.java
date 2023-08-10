package com.wanted.note.mapper;

import com.wanted.member.entity.Member;
import com.wanted.note.dto.NotePatchDto;
import com.wanted.note.dto.NotePostDto;
import com.wanted.note.dto.NoteResponseDto;
import com.wanted.note.entity.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note notePostDtoToNote(NotePostDto notePostDto);

    Note notePatchDtoToNote(NotePatchDto notePatchDto);

    List<NoteResponseDto> notesToNoteResponseDtos(List<Note> notes);

    default NoteResponseDto noteToNoteResponseDto(Note note) {
        return NoteResponseDto.builder()
                .noteId(note.getNoteId())
                .writer(note.getMember().getEmail())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    };
}
