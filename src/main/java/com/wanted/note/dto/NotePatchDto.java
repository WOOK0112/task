package com.wanted.note.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NotePatchDto {

    private String title;

    private String content;
}
