package org.greenSnake.enums;

import lombok.Getter;

@Getter
public enum UsersRoles {
    ROLE_ADMIN("ADMIN"),ROLE_USER("USER"),GUEST_ROLE("GUEST");
    private String role;

    UsersRoles(String role){
        this.role = role;
    }
}
