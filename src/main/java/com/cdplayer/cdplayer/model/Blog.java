package com.cdplayer.cdplayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotNull
    @NotBlank(message = "제목을 입력하세요.")
    private String title;
    @NotNull
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    private Date regDate;
    private String writer;
    private String file;
}
