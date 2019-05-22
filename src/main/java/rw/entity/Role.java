package rw.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Chebotar_do on 22.05.2019.
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    public String getAuthority() {
        return name();
    }
}
