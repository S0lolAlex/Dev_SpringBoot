package org.greenSnake.entities;

import lombok.Data;
import org.greenSnake.enums.UsersRoles;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    @Enumerated(EnumType.STRING)
    private UsersRoles role;
    private int enabled;

    public Users(@NonNull String username, @NonNull String password, @NonNull UsersRoles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
