package org.greenSnake.dto;

import lombok.Builder;
import lombok.Data;
import org.greenSnake.enums.UsersRoles;
@Data
@Builder
public class UserDto {
    private long id;
    private String username;
    private String password;
    private UsersRoles role;
    private int enabled;
}
