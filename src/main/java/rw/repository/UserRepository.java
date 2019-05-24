package rw.repository;

import org.springframework.security.core.userdetails.UserDetails;
import rw.entity.User;

import java.time.LocalDate;

/**
 * Created by Chebotar_do on 22.05.2019.
 */
public interface UserRepository {
    User findUserByLogin(String login);

    boolean createUser(String login, String password, String name, String patronymic, String surname, String passportSeries, String passportNumber, LocalDate passportIssueDate, String passportIssueBy, String email);
}
