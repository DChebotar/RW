package rw.repository;

import rw.entity.User;

import java.time.LocalDate;

/**
 * Created by Chebotar_do on 22.05.2019.
 */
public interface UserRepository {
    User findUserByLogin(String login);

    boolean saveUser(User user);
}
