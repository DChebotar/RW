package rw.repository;

import org.springframework.security.core.userdetails.UserDetails;
import rw.entity.User;

/**
 * Created by Chebotar_do on 22.05.2019.
 */
public interface UserRepository {
    User findUserByLogin(String login);
}
