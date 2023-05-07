package org.greenSnake.enums;

import lombok.Getter;

@Getter
public enum UsersRoles {
    ADMIN_ROLE("ADMIN"),USER_ROLE("USER"),GUEST_ROLE("GUEST");
    private String role;

    UsersRoles(String role){
        this.role = role;
    }
}
