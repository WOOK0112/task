package com.wanted.note.controller;

import com.wanted.note.dto.NotePatchDto;
import com.wanted.note.dto.NotePostDto;
import com.wanted.note.entity.Note;
import com.wanted.note.mapper.NoteMapper;
import com.wanted.note.pagination.MultiResponseDto;
import com.wanted.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/notes")
@Validated
@Slf4j
public class NoteController {
    private NoteService noteService;
    private NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @PostMapping
    public ResponseEntity postNote(@Valid @RequestBody NotePostDto notePostDto) {
        Note note = noteService.createNote(noteMapper.notePostDtoToNote(notePostDto), getAuthenticatedEmail());

        return new ResponseEntity(noteMapper.noteToNoteResponseDto(note), HttpStatus.CREATED);
    }

    @PatchMapping("/{note-id}")
    public ResponseEntity patchNote(@Valid @PathVariable("note-id") long noteId,
                                    @Valid @RequestBody NotePatchDto notePatchDto) {
        log.info("인증된이메일 :", getAuthenticatedEmail());
        Note note = noteService.updateNote(notePatchDto, noteId, getAuthenticatedEmail());

        return new ResponseEntity(noteMapper.noteToNoteResponseDto(note), HttpStatus.OK);
    }

    @GetMapping("/{note-id}")
    public ResponseEntity getNote(@Valid @PathVariable("note-id") long noteId) {
        Note note = noteService.findNote(noteId);

        return new ResponseEntity(noteMapper.noteToNoteResponseDto(note), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getNotes(@Positive @RequestParam("page") int page,
                                   @Positive @RequestParam("size") int size) {
        Page<Note> pageNote = noteService.findNotes(page - 1, size);
        List<Note> notes = pageNote.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(noteMapper.notesToNoteResponseDtos(notes),
                        pageNote),
                HttpStatus.OK);
    }

    @DeleteMapping("/{note-id}")
    public ResponseEntity deleteNote(@Valid @PathVariable("note-id") long noteId) {
        noteService.deleteNote(noteId, getAuthenticatedEmail());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private String getAuthenticatedEmail(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
    }
}
