package com.wanted.note.service;

import com.wanted.exception.BusinessLogicException;
import com.wanted.exception.ExceptionCode;
import com.wanted.member.entity.Member;
import com.wanted.member.service.MemberService;
import com.wanted.note.dto.NotePatchDto;
import com.wanted.note.entity.Note;
import com.wanted.note.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;
    private final MemberService memberService;

    public NoteService(NoteRepository noteRepository, MemberService memberService) {
        this.noteRepository = noteRepository;
        this.memberService = memberService;
    }

    public Note createNote(Note note, String authenticatedEmail) {
        Member member = memberService.findVerifiedMemberByEmail(authenticatedEmail);
        note.setMember(member);

        return noteRepository.save(note);
    }

    public Note findNote(long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        return optionalNote.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.NOTE_NOT_FOUND)
        );
    }

    public Page<Note> findNotes(int page, int size) {
        return noteRepository.findAll(PageRequest.of(page, size,
                Sort.by("noteId").descending()));
    }

    public Note updateNote(NotePatchDto patchDto, long noteId, String authenticatedEmail) {
        Note findNote = findNote(noteId);

        verifyMemberForUpdate(findNote, authenticatedEmail);

        Optional.ofNullable(patchDto.getTitle())
                .ifPresent(title->findNote.setTitle(title));
        Optional.ofNullable(patchDto.getContent())
                .ifPresent(content->findNote.setContent(content));

        return noteRepository.save(findNote);
    }

    public void deleteNote(long noteId, String authenticatedEmail) {
        Note findNote = findNote(noteId);

        if(findNote.getMember().getEmail().equals(authenticatedEmail) == false)
            throw new BusinessLogicException(ExceptionCode.NOTE_CANNOT_DELETE);

        noteRepository.delete(findNote);
    }

    public void verifyMemberForUpdate(Note note, String authenticatedEmail){
        if(note.getMember().getEmail()
                .equals(authenticatedEmail) == false) {
            throw new BusinessLogicException(ExceptionCode.NOTE_CANNOT_UPDATE);
        }
    }
}
