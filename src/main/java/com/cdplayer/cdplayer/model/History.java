package com.cdplayer.cdplayer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class History implements Comparable<History>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date date;
    private Date regDate;
    private String content;
    private String photo;

    @Override
    public int compareTo(History history) {
        return this.getDate().compareTo(history.getDate());
    }
}
