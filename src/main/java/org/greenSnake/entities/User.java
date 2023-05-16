package org.greenSnake.entities;

import lombok.Builder;
import lombok.Data;
import org.greenSnake.dto.UserDto;
import org.greenSnake.enums.UsersRoles;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    @Enumerated(EnumType.STRING)
    private UsersRoles role;
    private int enabled;
    public UserDto toUserDto(){
        UserDto userDto = UserDto.builder()
                .id(getId())
                .role(getRole())
                .enabled(getEnabled())
                .username(getUsername()).build();
        return userDto;
    }
}
