package org.greenSnake.entities;

import lombok.Data;
import org.greenSnake.enums.UsersRoles;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private UsersRoles role;
    @Nullable
    private boolean enabled;

    public Users(int id, @NonNull String username, @NonNull String password, @NonNull UsersRoles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
