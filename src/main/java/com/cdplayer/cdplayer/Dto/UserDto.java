package com.cdplayer.cdplayer.Dto;

import com.cdplayer.cdplayer.model.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(long id, String username, String password) {
        this.id=id;
        this.username=username;
        this.password=password;
    }
}
