package com.cdplayer.cdplayer.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @Builder
    public User(long id,String username, String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }
}
