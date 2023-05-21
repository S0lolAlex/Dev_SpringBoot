package org.greenSnake.entities;

import lombok.Data;
import org.greenSnake.dto.UserDto;
import org.greenSnake.enums.UsersRoles;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String username;
    @Nullable
    private String password;
    @Nullable
    @Enumerated(EnumType.STRING)
    private UsersRoles role;
    private Integer enabled;

    public UserDto toUserDto(){
        UserDto userDto = UserDto.builder()
                .id(getId())
                .role(getRole())
                .enabled(getEnabled())
                .username(getUsername()).build();
        return userDto;
    }
}
