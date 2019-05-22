package rw.repository;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Chebotar_do on 22.05.2019.
 */
public interface UserRepository {
    UserDetails findByUsername(String login);
}
